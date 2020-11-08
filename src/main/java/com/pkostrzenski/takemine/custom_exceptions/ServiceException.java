package com.pkostrzenski.takemine.custom_exceptions;

public class ServiceException extends Exception {

    private int errorCode;

    public ServiceException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
