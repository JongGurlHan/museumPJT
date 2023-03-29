package com.jghan.museumPJT.config;


import com.jghan.museumPJT.service.user.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig{

    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 비활성화
        http.authorizeRequests()
                //.antMatchers("/user/**", "/image/**", "/follow/**", "/comment/**", "/api/**").authenticated() //인증이 필요한 url
                .anyRequest().permitAll() //어떤 요청이라도 허용.
                .and()
                .formLogin()
                .loginPage("/user/login") //get
                .loginProcessingUrl("/user/login") //post
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2DetailsService);
        return http.build();


    }
}