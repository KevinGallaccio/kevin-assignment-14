package com.coderscampus.kevinassignment14.repository;

import com.coderscampus.kevinassignment14.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private Map<Long, User> userMap = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public void save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        userMap.put(user.getId(), user);
    }

    public User findById(long id) {
        return userMap.get(id);
    }

    public User findByUsername(String username) {
        for (User user : userMap.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public void delete(User user) {
        userMap.remove(user.getId());
    }
}
