package com.jghan.museumPJT.service.user;

import com.jghan.museumPJT.domain.user.User;
import com.jghan.museumPJT.dto.user.UserProfileDto;

public interface UserService {

    User join(User user);

	User update(int id, User entity);

    UserProfileDto userProfile(int pageUserId, int principalId);
}
