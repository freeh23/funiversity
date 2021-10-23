package com.switchfully.funiversity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws Exception {
        //List<String> stackStrings = Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList());
        //TODO print the trace line by line
        logger.warn(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
