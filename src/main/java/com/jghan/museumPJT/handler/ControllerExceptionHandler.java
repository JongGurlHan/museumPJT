package com.jghan.museumPJT.handler;

import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.handler.ex.CustomValidationException;
import com.jghan.museumPJT.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    //사용처: 회원가입 유효성 검사
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }

}
