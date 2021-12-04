package com.example.ems_3.service.ex;

public class IsEmptyException extends ServiceException {
    public IsEmptyException() {
        super();
    }

    public IsEmptyException(String message) {
        super(message);
    }

    public IsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IsEmptyException(Throwable cause) {
        super(cause);
    }

    protected IsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
