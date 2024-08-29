package com.pragma.restaurantcrud.domain.api;

import com.pragma.restaurantcrud.domain.models.EmployeeRestaurant;
import com.pragma.restaurantcrud.domain.models.Order;
import org.springframework.data.domain.Page;

public interface IEmployeeService {

    EmployeeRestaurant createEmployeeRestaurant(EmployeeRestaurant employeeRestaurant, String token);


}
