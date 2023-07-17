package com.jghan.museumPJT.service.user;

import com.jghan.museumPJT.config.user.PrincipalDetails;
import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@EnableWebSecurity
@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("Oauth2 서비스 탐");

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> userinfo = oAuth2User.getAttributes();

        System.out.println(userinfo);
        String email = (String)userinfo.get("email");
        String[] emailParts = email.split("@");

        String username = "google_"+emailParts[0];
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){ //페이스북 최초 로그인
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role("ROLE_USER")
                    .build();
            return new PrincipalDetails(userRepository.save(user), oAuth2User.getAttributes());
        }else{ //이전에 페이스북 로그인
            userRepository.updateLastLogin(userEntity.getId());
            userRepository.updateLoginCount(userEntity.getId());
            return new PrincipalDetails(userEntity, oAuth2User.getAttributes()) ;
        }

        //https://datamoney.tistory.com/333 소셜로그인 추가

        //{sub=106678981716946556905,
        // name=JG HAN,
        // given_name=JG,
        // family_name=HAN,
        // picture=https://lh3.googleusercontent.com/a/AGNmyxa7W2YhtfvSgvNxei1cmQi_3GutpLk3oCeiUPMt=s96-c,
        // email=gojkhan13@gmail.com,
        // email_verified=true,
        // locale=ko}

    }
}
