package com.jghan.museumPJT.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.dto.CMRespDto;
import com.jghan.museumPJT.dto.user.UserUpdateDto;
import com.jghan.museumPJT.handler.ex.CustomValidationException;
import com.jghan.museumPJT.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {
	
    private final UserService userService;

	
	@PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, // 꼭 @Valid가 적혀있는 다음 파라미터에 적어야됨.
                               @AuthenticationPrincipal PrincipalDetails principalDetails){
		
			if(bindingResult.hasErrors()) {
				
				Map<String, String> errorMap = new HashMap<>();
				
				for(FieldError error : bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				throw new CustomValidationException("유효성 검사 실패함", errorMap);
				
			}


            User userEntity = userService.update(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity); //수정된 값에 따라 세션정보 변경
            return new CMRespDto<>(1, "회원수정완료", userEntity); //응답시에 userEntity의 모든 getter함수가 호출되고 JSON으로 파싱하여 응답한다.



    }

}
