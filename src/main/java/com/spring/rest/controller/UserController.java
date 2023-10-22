package com.spring.rest.controller;

import com.spring.rest.model.User;
import com.spring.rest.service.UserService;
import com.spring.rest.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.rest.configuration.Constants.Messages.USER_DELETED_MESSAGE;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/findAll")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public UserDto saveOrUpdateUser(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return String.format(USER_DELETED_MESSAGE, id);
    }
}
