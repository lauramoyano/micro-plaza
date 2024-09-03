package com.pragma.restaurantcrud.domain.usecase;

import com.pragma.restaurantcrud.domain.api.IEmployeeService;
import com.pragma.restaurantcrud.domain.models.*;
import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IMessage;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import com.pragma.restaurantcrud.infrastructure.output.client.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUsecase implements IEmployeeService{

    private final IEmployeePersistencePort employeePersistencePort;
    private final IGateway     userGateway;
    private final JwtProvider jwtProvider;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IMessage messengerService;

    public EmployeeUsecase(IEmployeePersistencePort employeePersistencePort,
            IRestaurantPersistencePort restaurantPersistencePort , IOrderPersistencePort orderPersistencePort,  IGateway userGateway, JwtProvider jwtProvider, IMessage messengerService) {
        this.employeePersistencePort = employeePersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.userGateway = userGateway;
        this.jwtProvider = jwtProvider;
        this.messengerService = messengerService;
    }

    @Override
    public EmployeeRestaurant createEmployeeRestaurant(EmployeeRestaurant employeeRestaurant, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);
        if (user == null)
            throw new IllegalArgumentException("User not found");
        final Restaurant restaurant =  restaurantPersistencePort.findRestaurantById(employeeRestaurant.getIdRestaurant());
        if (restaurant == null)
            throw new IllegalArgumentException("Restaurant not found");
        employeeRestaurant.setIdRestaurant(restaurant.getIdRestaurant());
        return this.employeePersistencePort.save(employeeRestaurant);
    }



    @Override
    public Page<Order> getAllOrdersFilterByStatusAndSizeItemsByPage(Integer size, Integer page, String status, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);
        EmployeeRestaurant userEmployeeFound = this.employeePersistencePort.findById(user.getId());
        //find restaurant by employee and validate if exists
        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(userEmployeeFound.getIdRestaurant());
        if (restaurant == null)
            throw new IllegalArgumentException("Restaurant not found");

        final Page<Order> orders = this.orderPersistencePort.findAllOrdersByStatusAndSizeItemsByPage(PageRequest.of(page, size), restaurant.getIdRestaurant(), status);
        if(orders.isEmpty())
            throw new IllegalArgumentException("There are no orders");

        return orders;
    }
    @Override
    public List<Order> assignEmployeeToOrderAndChangeStatusToInPreparation(List<Long> idOrders, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);
        EmployeeRestaurant userEmployeeFound = this.employeePersistencePort.findById(user.getId());
        Long idRestaurant = userEmployeeFound.getIdRestaurant();
        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(idRestaurant);

        if (restaurant == null)
            throw new IllegalArgumentException("Restaurant not found");

        List<Order> ordersAssigned = new ArrayList<>();

        for (Long idOrder : idOrders) {
            Order orderFoundToAssignEmployee = this.orderPersistencePort.findById(idOrder);

            if (orderFoundToAssignEmployee == null) {
                throw new IllegalArgumentException("The order does not exist");
            } else if (orderFoundToAssignEmployee.getIdEmployeeRestaurant() != null) {
                throw new IllegalArgumentException("This order was assigned to any employee");
            } else if (!userEmployeeFound.getIdRestaurant().equals(orderFoundToAssignEmployee.getRestaurant().getIdRestaurant())) {
                throw new IllegalArgumentException("The employee does not belong to this restaurant");
            }

            orderFoundToAssignEmployee.setIdEmployeeRestaurant(userEmployeeFound);
            orderFoundToAssignEmployee.setStatus("IN_PREPARATION");
            this.orderPersistencePort.save(orderFoundToAssignEmployee);
            ordersAssigned.add(orderFoundToAssignEmployee);
        }
        return ordersAssigned;
    }

    @Override
    public Order orderReadyAndNotifyCustomer(Long idOrder, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);
        EmployeeRestaurant userEmployeeFound = this.employeePersistencePort.findById(user.getId());
        Long idRestaurant = userEmployeeFound.getIdRestaurant();
        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(idRestaurant);

        if (restaurant == null)
            throw new IllegalArgumentException("Restaurant not found");

        Order orderFound = this.orderPersistencePort.findById(idOrder);
        if (orderFound == null) {
            throw new IllegalArgumentException("The order does not exist");
        } else if (!userEmployeeFound.getIdRestaurant().equals(orderFound.getRestaurant().getIdRestaurant())) {
            throw new IllegalArgumentException("The employee does not belong to this restaurant");
        } else if (!orderFound.getStatus().equals("IN_PREPARATION")) {
            throw new IllegalArgumentException("The order is not in preparation");
        }
        UserDto userCustomerToNotifyOfYourOrder = this.userGateway.getUserById( orderFound.getIdCustomer(), token);
        Long pinGenerated = encryptOrderId(orderFound.getIdOrder());
        final String message = "Your order is ready to be delivered, the pin is: " + pinGenerated;
        PinMessage pinMessage = new PinMessage(pinGenerated, userCustomerToNotifyOfYourOrder.getName(),  userCustomerToNotifyOfYourOrder.getPhone(), restaurant.getName(), message);
         messengerService.sendNotification(pinMessage, token);
        orderFound.setStatus("READY");
        this.orderPersistencePort.save(orderFound);
        return orderFound;
    }

    @Override
    public Order orderDelivered(Long idOrder,  String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);
        EmployeeRestaurant userEmployeeFound = this.employeePersistencePort.findById(user.getId());
        Long idRestaurant = userEmployeeFound.getIdRestaurant();
        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(idRestaurant);
        if (restaurant == null)
            throw new IllegalArgumentException("Restaurant not found");
        Order orderFound = this.orderPersistencePort.findById(decryptOrderPin(String.valueOf(idOrder)));
        if (orderFound == null) {
            throw new IllegalArgumentException("The order does not exist");
        } else if (!orderFound.getStatus().equals("READY")) {
            throw new IllegalArgumentException("The order is not ready");
        }else if (orderFound.getIdEmployeeRestaurant() == null) {
            throw new IllegalArgumentException("The order has not been assigned to any employee");
        }else if (!userEmployeeFound.getIdRestaurant().equals(orderFound.getRestaurant().getIdRestaurant())) {
            throw new IllegalArgumentException("The employee does not belong to this restaurant");
        }
        orderFound.setStatus("DELIVERED");
        this.orderPersistencePort.save(orderFound);
        return orderFound;
    }

    private Long decryptOrderPin(String pinEncryption) {
        StringBuilder decryptPinFromOrder = new StringBuilder();
        for (int index = 0; index < pinEncryption.length(); index++) {
            char encryptedPinDigit = pinEncryption.charAt(index);
            int decryptedPinDigit = (Character.getNumericValue(encryptedPinDigit) + 7) % 10;
            decryptPinFromOrder.append(decryptedPinDigit);
        }
        return Long.parseLong(decryptPinFromOrder.toString());
    }
    private long encryptOrderId(Long idOrder) {
        String addFiveCharacters = String.format("%05d", idOrder);
        StringBuilder orderIdEncryption = new StringBuilder();
        for (int index = 0; index < addFiveCharacters.length(); index++) {
            char digitToEncrypt  = addFiveCharacters.charAt(index);
            int originalCharacterToEncrypt = Character.getNumericValue(digitToEncrypt);
            orderIdEncryption.append((originalCharacterToEncrypt + 3) % 10);
        }
        return Long.parseLong(orderIdEncryption.toString());
    }


}

