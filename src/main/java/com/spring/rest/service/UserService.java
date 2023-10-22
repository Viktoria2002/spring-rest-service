package com.spring.rest.service;

import com.spring.rest.model.User;
import com.spring.rest.service.dto.UserDto;

public interface UserService extends Service<UserDto, User, Long> {
    UserDto saveOrUpdate(User user);
}
