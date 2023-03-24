package com.jghan.museumPJT.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.handler.ex.CustomValidationApiException;
import com.jghan.museumPJT.handler.ex.CustomValidationException;
import com.jghan.museumPJT.util.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    //사용처: 회원가입 , 회원정보 수정 유효성 검사
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }
    
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST );
    	
    }

}
