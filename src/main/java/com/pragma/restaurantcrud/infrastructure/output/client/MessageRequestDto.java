package com.pragma.restaurantcrud.infrastructure.output.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {

        private Long pin;
        private String nameCustomer;
        private String phoneNumber;
        private String restaurantName;
        private String message;

}
