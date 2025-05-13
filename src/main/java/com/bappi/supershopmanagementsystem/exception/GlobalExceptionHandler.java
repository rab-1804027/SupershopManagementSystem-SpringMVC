package com.bappi.supershopmanagementsystem.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException exception) {
        log.error("A RuntimeException occured: {}", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        log.error("An Exception occured: {}", exception.getMessage());
        return "error";
    }
}
