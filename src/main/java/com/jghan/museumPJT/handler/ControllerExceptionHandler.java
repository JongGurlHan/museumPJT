package com.jghan.museumPJT.handler;

import com.jghan.museumPJT.handler.ex.CustomException;
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

    //사용처: 회원가입
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }
    //사용처: 회원정보 수정 유효성 검사
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		return new ResponseEntity(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST );
    }

    //사용처: 회원프로필 페이지조회
    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e){
        return  Script.back(e.getMessage());
    }


}
