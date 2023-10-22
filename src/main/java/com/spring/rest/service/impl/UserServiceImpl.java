package com.spring.rest.service.impl;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;
import com.spring.rest.service.UserService;
import com.spring.rest.service.dto.UserDto;
import com.spring.rest.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.rest.configuration.Constants.ExceptionMessages.USER_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getById(Long id) {
        User user = findById(id);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toDto(users);
    }

    @Override
    public UserDto saveOrUpdate(User user) {
        return UserMapper.INSTANCE.toDto(userRepository.save(user));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }
}
