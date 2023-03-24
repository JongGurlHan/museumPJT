package com.jghan.museumPJT.service.user;

import com.jghan.museumPJT.domain.user.User;

public interface UserService {

    User join(User user);

	User update(int id, User entity);
}
