package com.jghan.museumPJT.controller.user;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.dto.user.JoinDto;
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
    public String signup(@Valid JoinDto signupDto, BindingResult bindingResult) { //key=value (x-www-form-urlencoded)



        log.info(signupDto.toString());

        //User < - SingupDto
        User user = signupDto.toEntity();
        userService.join(user);
        log.info(user.toString());

        return "auth/login";

    }

}
