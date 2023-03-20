package com.jghan.museumPJT.dto.user;

import com.jghan.museumPJT.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JoinDto {
    @Size(min=2, max = 20)
    @NotBlank
    private  String username;

    @NotBlank
    private  String password;

    @NotBlank
    private  String email;

    @NotBlank
    private  String name;

    @NotBlank
    private  String sex;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .sex(sex)
                .build();
    }
}
