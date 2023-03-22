package com.jghan.museumPJT.handler;

import com.jghan.museumPJT.handler.ex.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public Map<String, String> validationException(CustomValidationException e){
        return e.getErrorMap();
    }

}
