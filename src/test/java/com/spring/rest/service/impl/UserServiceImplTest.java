package com.spring.rest.service.impl;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;
import com.spring.rest.service.dto.UserDto;
import com.spring.rest.service.impl.UserServiceImpl;
import com.spring.rest.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private List<User> users;

    @BeforeEach
    public void setUp() {
        user = new User(1L, "LN1", "FN1", "email1", "pass1");
        User user2 = new User(2L, "LN2", "FN2", "email2", "pass2");
        users = Arrays.asList(user, user2);
    }

    @Test
    void testGetByNonExistingUserId() {
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getById(userId);
        });
    }

    @Test
    void testGetByExistingUserId() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        UserDto expectedDto = UserMapper.INSTANCE.toDto(user);

        UserDto actualDto = userService.getById(1L);

        assertNotNull(actualDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testDeleteById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        userService.deleteById(1L);

        verify(userRepository).delete(user);
    }

    @Test
    void testFindAll() {
        int expectedDtosSize = 2;

        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> actualDtos = userService.findAll();

        assertNotNull(actualDtos);
        assertEquals(expectedDtosSize, actualDtos.size());
    }

    @Test
    void testSaveOrUpdate() {
        when(userRepository.save(any())).thenReturn(user);
        UserDto expectedDto = UserMapper.INSTANCE.toDto(user);

        UserDto actualDto = userService.saveOrUpdate(user);

        assertEquals(expectedDto, actualDto);
    }
}
