package com.pragma.restaurantcrud.infrastructure.output.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

        private Long id;
        private String name;
        private String lastName;
        private String cc;
        private String phone;
        private String birthDate;
        private String email;
        private String rolName;
}
