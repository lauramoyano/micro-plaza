package com.pragma.restaurantcrud.domain.exeptions;

public  enum  ExceptionResponse {

        INVALID_DATA("Rectify the field format is incorrect"),
        NOT_FOUND("The requested resource was not found"),
        EMPTY_FIELDS("The fields cannot be empty"),
        ALREADY_EXIST("The resource already exists"),
        NOT_BELONG("The resource does not belong to the user");

        private final String message;

        ExceptionResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
}
