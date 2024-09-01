package com.pragma.restaurantcrud.domain.usecase;

import com.pragma.restaurantcrud.domain.api.IEmployeeService;
import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.models.Order;
import com.pragma.restaurantcrud.domain.models.Restaurant;
import com.pragma.restaurantcrud.domain.models.User;
import com.pragma.restaurantcrud.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.restaurantcrud.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.restaurantcrud.domain.spi.servicePortClient.IGateway;
import com.pragma.restaurantcrud.infrastructure.config.securityClient.JwtProvider;
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

    public EmployeeUsecase(IEmployeePersistencePort employeePersistencePort,
            IRestaurantPersistencePort restaurantPersistencePort , IOrderPersistencePort orderPersistencePort,  IGateway userGateway, JwtProvider jwtProvider) {
        this.employeePersistencePort = employeePersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.orderPersistencePort = orderPersistencePort;
        this.userGateway = userGateway;
        this.jwtProvider = jwtProvider;
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







}

