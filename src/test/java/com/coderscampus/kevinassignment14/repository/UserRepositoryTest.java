package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    @DisplayName("Should create and save a new user and this user should be found by its Id")
    void testSaveAndFindById() {
        //Arrange
        UserRepository userRepository = new UserRepository();
        String username = "testUser";
        User user = new User(username);

        //Act
        userRepository.save(user);
        User savedUser = userRepository.findById(user.getId());

        //Assert
        assertNotNull(savedUser);
        assertEquals(username, savedUser.getUsername());
    }


    @Test
    @DisplayName("User should be found by its username")
    void findByUsername() {
        // Arrange
        UserRepository userRepository = new UserRepository();
        String username1 = "testUser1";
        String username2 = "testUser2";
        User user1 = new User(username1);
        User user2 = new User(username2);
        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        User savedUser1 = userRepository.findByUsername(username1);
        User savedUser2 = userRepository.findByUsername(username2);
        User nonExistentUser = userRepository.findByUsername("nonExistentUser");

        // Assert
        assertNotNull(savedUser1);
        assertEquals(username1, savedUser1.getUsername());

        assertNotNull(savedUser2);
        assertEquals(username2, savedUser2.getUsername());

        assertNull(nonExistentUser);
    }

    @Test
    @DisplayName("All Users should be found within the repository")
    void findAll() {
        // Arrange
        UserRepository userRepository = new UserRepository();
        String username1 = "testUser1";
        String username2 = "testUser2";
        User user1 = new User(username1);
        User user2 = new User(username2);
        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        List<User> users = userRepository.findAll();
        User nonExistentUser = userRepository.findByUsername("nonExistentUser");

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertFalse(users.contains(nonExistentUser));
    }

    @Test
    @DisplayName("User should be correctly deleted")
    void delete() {
        //Arrange
        UserRepository userRepository = new UserRepository();
        String username = "testUser";
        User user = new User(username);
        userRepository.save(user);

        //Act
        userRepository.delete(user);
        User deletedUser = userRepository.findById(user.getId());

        //Assert
        assertNull(deletedUser);
        for (User u : userRepository.findAll()) {
            assertNotEquals(user.getId(), u.getId());
        }
    }
}