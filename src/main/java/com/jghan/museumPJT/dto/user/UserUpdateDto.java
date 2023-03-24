package com.jghan.museumPJT.dto.user;

import javax.validation.constraints.NotBlank;

import com.jghan.museumPJT.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
    
	@NotBlank
    private String name; //필수
    @NotBlank
    private String password; //필수

    public User toEntity(){
        return User.builder()
                .name(name) //이름을 기재 안하면 문제 -> validation체크
                .password(password) //비번을 기재 안하면 문제 -> validation체크             
                .build();
    }
}
