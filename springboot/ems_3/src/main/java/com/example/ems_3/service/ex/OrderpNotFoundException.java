package com.example.ems_3.service.ex;

public class OrderpNotFoundException extends ServiceException {
    public OrderpNotFoundException() {
        super();
    }

    public OrderpNotFoundException(String message) {
        super(message);
    }

    public OrderpNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderpNotFoundException(Throwable cause) {
        super(cause);
    }

    protected OrderpNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
