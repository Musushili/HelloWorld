package com.example.ems_3.service.ex;

public class OrderupNotFoundException extends ServiceException {
    public OrderupNotFoundException() {
        super();
    }

    public OrderupNotFoundException(String message) {
        super(message);
    }

    public OrderupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderupNotFoundException(Throwable cause) {
        super(cause);
    }

    protected OrderupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
