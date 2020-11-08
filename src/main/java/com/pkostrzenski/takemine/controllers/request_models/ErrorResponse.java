package com.pkostrzenski.takemine.controllers.request_models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*
    Universal class that lets me write more elegant code in API controllers
    With this, i don't have to write all of these long function names etc.
    I think it makes the code more readable (maybe it is'nt but who knows)
 */
public class ErrorResponse {

    private static class ErrorMessage {

        private String message;
        private int status;
        private Integer errorCode;

        public ErrorMessage(String message, int status, Integer errorCode) {
            this.message = message;
            this.status = status;
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        public Integer getErrorCode() {
            return errorCode;
        }
    }

    public static ResponseEntity<ErrorMessage> create(HttpStatus status, String message){
        return new ResponseEntity<>(new ErrorMessage(message, status.value(), null), status);
    }

    public static ResponseEntity<ErrorMessage> create(HttpStatus status, String message, Integer errorCode){
        return new ResponseEntity<>(new ErrorMessage(message, status.value(), errorCode), status);
    }

    public static ResponseEntity<ErrorMessage> unauthorized(){
        return new ResponseEntity<>(
                new ErrorMessage("You are not allowed to do this", HttpStatus.UNAUTHORIZED.value(), null),
                HttpStatus.UNAUTHORIZED
        );
    }
}