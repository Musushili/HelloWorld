package com.example.ems_3.service.ex;

public class TokenAuthenticationException extends ServiceException{
    public TokenAuthenticationException() {
        super();
    }

    public TokenAuthenticationException(String message) {
        super(message);
    }

    public TokenAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenAuthenticationException(Throwable cause) {
        super(cause);
    }

    protected TokenAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
