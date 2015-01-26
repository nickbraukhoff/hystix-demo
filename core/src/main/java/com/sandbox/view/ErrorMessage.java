package com.sandbox.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author tkmay02
 * @since 1/25/15
 */
@JsonAutoDetect
public class ErrorMessage {

    private final int code;
    private final String message;

    public ErrorMessage(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
