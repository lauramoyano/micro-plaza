package com.pragma.restaurantcrud.domain.usecase;

import com.pragma.restaurantcrud.domain.api.IEmployeeService;
import com.pragma.restaurantcrud.domain.exeptions.AlreadyExist;
import com.pragma.restaurantcrud.domain.exeptions.InvalidData;
import com.pragma.restaurantcrud.domain.exeptions.NotBelong;
import com.pragma.restaurantcrud.domain.exeptions.NotFound;
import com.pragma.restaurantcrud.domain.models.*;
import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IMessage;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.ITraceability;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
import com.pragma.restaurantcrud.infrastructure.output.client.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeUsecase implements IEmployeeService{

    private final IEmployeePersistencePort employeePersistencePort;
    private final IGateway     userGateway;
    private final JwtProvider jwtProvider;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IMessage messengerService;
    private  final ITraceability traceabilityService;

    public EmployeeUsecase(IEmployeePersistencePort employeePersistencePort,
            IRestaurantPersistencePort restaurantPersistencePort , IOrderPersistencePort orderPersistencePort,  IGateway userGateway, JwtProvider jwtProvider, IMessage messengerService, ITraceability traceabilityService) {
        this.employeePersistencePort = employeePersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.userGateway = userGateway;
        this.jwtProvider = jwtProvider;
        this.messengerService = messengerService;
        this.traceabilityService = traceabilityService;
    }

    @Override
    public EmployeeRestaurant createEmployeeRestaurant(EmployeeRestaurant employeeRestaurant, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);

        if (user == null)
            throw new NotFound("User not found");
        final Restaurant restaurant =  restaurantPersistencePort.findRestaurantById(employeeRestaurant.getIdRestaurant());
        if (restaurant == null)
            throw new NotFound("Restaurant not found");

        if (!restaurant.getIdOwner().equals(user.getId()))
            throw new NotBelong("The user is not the owner of the restaurant");
        employeeRestaurant.setIdRestaurant(restaurant.getIdRestaurant());
        return this.employeePersistencePort.save(employeeRestaurant);
    }



    @Override
    public Page<Order> getAllOrdersFilterByStatusAndSizeItemsByPage(Integer size, Integer page, String status, String token) {
        String email = jwtProvider.getAuthentication(token.replace("Bearer ", "").trim()).getName();
        User user = this.userGateway.getUserByEmail(email, token);
        EmployeeRestaurant userEmployeeFound = this.employeePersistencePort.findById(user.getId());
        Restaurant restaurant = this.restaurantPersistencePort.findRestaurantById(userEmployeeFound.getIdRestaurant());
        if (restaurant == null)
            throw new NotFound("Restaurant not found");

        Page<Order> orders = this.orderPersistencePort.findAllOrdersByStatusAndSizeItemsByPage(PageRequest.of(page, size), restaurant.getIdRestaurant(), status);
        if(orders.isEmpty())
            throw new NotFound("There are no orders");

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
            throw new NotFound("Restaurant not found");

        List<Order> ordersAssigned = new ArrayList<>();

        for (Long idOrder : idOrders) {
            Order orderFoundToAssignEmployee = this.orderPersistencePort.findById(idOrder);
            String emailCustomer= this.userGateway.getUserById(orderFoundToAssignEmployee.getIdCustomer(), token).getEmail();

            if (orderFoundToAssignEmployee == null) {
                throw new NotFound("The order does not exist");
            } else if (orderFoundToAssignEmployee.getIdEmployeeRestaurant() != null) {
                throw new NotBelong("This order was assigned to any employee");
            } else if (!userEmployeeFound.getIdRestaurant().equals(orderFoundToAssignEmployee.getRestaurant().getIdRestaurant())) {
                throw new NotBelong("The employee does not belong to this restaurant");
            }

            orderFoundToAssignEmployee.setIdEmployeeRestaurant(userEmployeeFound);
            orderFoundToAssignEmployee.setStatus("IN_PREPARATION");
            this.orderPersistencePort.save(orderFoundToAssignEmployee);
            ordersAssigned.add(orderFoundToAssignEmployee);
            LocalDateTime date = LocalDateTime.now();
            String traceabilityId = UUID.randomUUID().toString();
            TraceabilityModel traceabilityModel =
                    new TraceabilityModel(traceabilityId,
                            orderFoundToAssignEmployee.getIdOrder().intValue(),
                            orderFoundToAssignEmployee.getIdCustomer().intValue(),
                            emailCustomer,
                            "IN_PREPARATION",
                            user.getId().intValue(),
                            user.getEmail(),
                            date,
                            date);
            traceabilityService.saveTraceability(traceabilityModel);
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
        LocalDateTime date = LocalDateTime.now();
        if (restaurant == null)
            throw new NotFound("Restaurant not found");

        Order orderFound = this.orderPersistencePort.findById(idOrder);

        if (orderFound == null) {
            throw new NotFound("The order does not exist");
        } else if (!userEmployeeFound.getIdRestaurant().equals(orderFound.getRestaurant().getIdRestaurant())) {
            throw new NotBelong("The employee does not belong to this restaurant");
        } else if (!orderFound.getStatus().equals("IN_PREPARATION")) {
            throw new InvalidData("The order is not in preparation");
        }
        LocalDateTime startDate = traceabilityService.getTraceability(orderFound.getIdOrder().intValue(), "IN_PREPARATION").getOrderStartDate();

        UserDto userCustomerToNotifyOfYourOrder = this.userGateway.getUserById( orderFound.getIdCustomer(), token);
        Long pinGenerated = encryptOrderId(orderFound.getIdOrder());
        String emailCustomer= this.userGateway.getUserById(orderFound.getIdCustomer(), token).getEmail();
        String message = "Your order is ready to be delivered, the pin is: " + pinGenerated;
        PinMessage pinMessage = new PinMessage(pinGenerated, userCustomerToNotifyOfYourOrder.getName(),  userCustomerToNotifyOfYourOrder.getPhone(), restaurant.getName(), message);
         messengerService.sendNotification(pinMessage, token);
        orderFound.setStatus("READY");
        String traceabilityId = UUID.randomUUID().toString();


        TraceabilityModel traceabilityModel =
                new TraceabilityModel(traceabilityId,
                        orderFound.getIdOrder().intValue(),
                        orderFound.getIdCustomer().intValue(),
                        emailCustomer,
                        "READY",
                        user.getId().intValue(),
                        user.getEmail(),
                        startDate,
                        date);
        traceabilityService.saveTraceability(traceabilityModel);
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
            throw new NotFound("Restaurant not found");
        Order orderFound = this.orderPersistencePort.findById(decryptOrderPin(String.valueOf(idOrder)));
        String emailCustomer= this.userGateway.getUserById(orderFound.getIdCustomer(), token).getEmail();
        if (orderFound == null) {
            throw new NotFound("The order does not exist");
        } else if (!orderFound.getStatus().equals("READY")) {
            throw new InvalidData("The order is not ready");
        }else if (orderFound.getIdEmployeeRestaurant() == null) {
            throw new NotBelong("The order has not been assigned to any employee");
        }else if (!userEmployeeFound.getIdRestaurant().equals(orderFound.getRestaurant().getIdRestaurant())) {
            throw new NotBelong("The employee does not belong to this restaurant");
        }
        LocalDateTime startDate = traceabilityService.getTraceability(orderFound.getIdOrder().intValue(), "IN_PREPARATION").getOrderStartDate();

        LocalDateTime date = LocalDateTime.now();
        String traceabilityId = UUID.randomUUID().toString();
        TraceabilityModel traceabilityModel =
                new TraceabilityModel(traceabilityId,
                        orderFound.getIdOrder().intValue(),
                        orderFound.getIdCustomer().intValue(),
                        emailCustomer,
                        "DELIVERED",
                        user.getId().intValue(),
                        user.getEmail(),
                        startDate,
                        date);
        traceabilityService.saveTraceability(traceabilityModel);
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

