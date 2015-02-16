package com.sandbox.exception;

/**
 * @author Nick Braukhoff
 * @since 1/25/15
 */
public class ServiceException extends RuntimeException {

    private int code;
    private String message;


    public ServiceException(final int code, final String message){
        this.code = code;
        this.message = message;
    }

    public ServiceException(final String message, final Throwable cause){
        super(message, cause);
    }
    public ServiceException(final String message){
        super(message);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
