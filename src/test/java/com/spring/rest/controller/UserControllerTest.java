package com.spring.rest.controller;

import com.spring.rest.model.User;
import com.spring.rest.service.UserService;
import com.spring.rest.service.dto.UserDto;
import com.spring.rest.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUser() {
        long orderId = 1;
        UserDto expectedDto = new UserDto();
        expectedDto.setId(orderId);

        when(userService.getById(orderId)).thenReturn(expectedDto);

        UserDto actualDto = userController.getUser(orderId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testGetAllUsers() {
        List<UserDto> expectedList = new ArrayList<>();
        expectedList.add(new UserDto());
        expectedList.add(new UserDto());

        when(userService.findAll()).thenReturn(expectedList);

        List<UserDto> actualList = userController.getUsers();

        assertEquals(expectedList, actualList);
    }

    @Test
    void testSaveUser() {
        User user = new User("LN1", "FN1", "email1", "pass1");
        UserDto expectedUserDto = UserMapper.INSTANCE.toDto(user);

        when(userService.saveOrUpdate(user)).thenReturn(expectedUserDto);

        UserDto actualUserDto = userController.saveOrUpdateUser(user);

        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void testUpdateUser() {
        User user = new User(1L, "LN1", "FN1", "email1", "pass1");
        UserDto expectedUserDto = UserMapper.INSTANCE.toDto(user);

        when(userService.saveOrUpdate(user)).thenReturn(expectedUserDto);

        UserDto actualUserDto = userController.saveOrUpdateUser(user);

        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void testDeleteUser() {
        long userId = 1;
        String expectedMessage = "User with ID = " + userId + " was deleted";

        String actualMessage = userController.deleteUser(userId);

        assertEquals(expectedMessage, actualMessage);
    }
}
