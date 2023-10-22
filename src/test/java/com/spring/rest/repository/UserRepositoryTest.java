package com.spring.rest.repository;

import com.spring.rest.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(JpaConfigTest.class)
@ExtendWith(PostgreSQLExtension.class)
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAll() {
        int expectedUsersSize = 3;

        List<User> users = userRepository.findAll();

        assertEquals(expectedUsersSize, users.size());
    }

    @Test
    void testFindById() {
        String expectedEmail = "email2";

        Optional<User> user = userRepository.findById(2L);

        assertTrue(user.isPresent());
        assertEquals(expectedEmail, user.get().getEmail());
    }

    @Test
    void testSave() {
        User user = new User("LN4", "FN4", "email4@mail.ru", "pass4");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
    }

    @Test
    void testUpdate() {
        User user = new User(2L, "newLN", "newFN", "newEmail@mail.ru", "newPass");

        User updatedUser = userRepository.save(user);

        assertEquals(user, updatedUser);
    }

    @Test
    void testDelete() {
        User user = new User(2L, "LN2", "FN2", "email2", "pass2");
        assertTrue(userRepository.findById(2L).isPresent());

        userRepository.delete(user);

        assertFalse(userRepository.findById(2L).isPresent());
    }
}
