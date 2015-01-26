package com.sandbox.controller;

import com.sandbox.exception.ServiceException;
import com.sandbox.view.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tkmay02
 * @since 1/15/15
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> vendorExceptions(final ServiceException serviceException) throws Exception {

        LOGGER.error("ErrorCode : [{}] | Message : [{}]", serviceException.getCode(), serviceException.getMessage());

        final ErrorMessage errorMessage = new ErrorMessage(serviceException.getCode(), serviceException.getMessage());

        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.valueOf(serviceException.getCode()));
    }
}
