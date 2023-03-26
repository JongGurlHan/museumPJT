package com.jghan.museumPJT.service.user;

import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.domain.user.UserRepository;
import com.jghan.museumPJT.dto.user.UserProfileDto;
import com.jghan.museumPJT.handler.ex.CustomException;
import com.jghan.museumPJT.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User join(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);
        return userEntity;
    }

    //회원정보 수정
	@Override
	@Transactional
	public User update(int id, User user) {

        User userEntity = userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 id입니다.");} );

        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);

		return userEntity;
	}

    //회원 프로필 조회
    @Override
    public UserProfileDto userProfile(int pageUserId, int principalId) {
        UserProfileDto dto = new UserProfileDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() ->{
           throw new CustomException("해당 프로필 페이지는 존재하지 않습니다.");
        });

        dto.setUser(userEntity);


        return dto;
    }


}
