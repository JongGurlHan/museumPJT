package com.jghan.museumPJT.config.user;

import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){
            return null;
        }else{
        	//최근 로그인 날짜 업데이트
        	userRepository.updateLastLogin(userEntity.getId());
        	userRepository.updateLoginCount(userEntity.getId());
            return new PrincipalDetails(userEntity);

        }

    }
}
