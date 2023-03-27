package com.jghan.museumPJT.controller.user;

import java.util.HashMap;

import javax.validation.Valid;

import com.jghan.museumPJT.dto.user.UserProfileDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.dto.user.JoinDto;
import com.jghan.museumPJT.handler.ex.CustomValidationException;
import com.jghan.museumPJT.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
	
    private final UserService userService;

	
	@GetMapping("/user/login")
	public String loginForm() {
		return "user/login";
	}
	
	@GetMapping("/user/join")
	public String joinForm() {
		return "user/join";
	}
	
	@PostMapping("/user/join")
    public String signup(@Valid JoinDto joinDto, BindingResult bindingResult) { //key=value (x-www-form-urlencoded)

		if(bindingResult.hasErrors()){
			HashMap<String, String> errorMap = new HashMap<>();

			for(FieldError error : bindingResult.getFieldErrors()){
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성검사 실패함", errorMap);

		}else{
			//User < - SingupDto
			User user = joinDto.toEntity();
			userService.join(user);
			return "user/login";
		}
    }


	//마이페이지
	@GetMapping({"/user/{pageUserId}"})
	public String profile (@PathVariable int pageUserId, Model model,
						   @AuthenticationPrincipal PrincipalDetails principalDetails){

		UserProfileDto userDto = userService.userProfile(pageUserId, principalDetails.getUser().getId());

		model.addAttribute("dto", userDto);
		return "user/profile";

	}

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
		System.out.println("세션정보:" + principalDetails.getUser());
		model.addAttribute("principal", principalDetails.getUser() );
		return "user/update";
	}

}
