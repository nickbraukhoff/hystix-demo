package com.sandbox.ws;

import org.springframework.http.HttpStatus;

/**
 * @author Nick Braukhoff
 * @since 1/15/15
 */
public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
