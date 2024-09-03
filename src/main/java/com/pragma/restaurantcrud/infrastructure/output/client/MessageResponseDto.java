package com.pragma.restaurantcrud.infrastructure.output.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponseDto {

        private String status;
        private String errorCode;
        private String errorMessage;
        private String priceMessage;
        private String direction;
        private LocalDate dateCreated;
        private LocalDate dateSent;
        private LocalDate dateUpdated;
}
