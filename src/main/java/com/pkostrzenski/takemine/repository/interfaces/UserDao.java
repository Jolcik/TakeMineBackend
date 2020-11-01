package com.pkostrzenski.takemine.repository.interfaces;

import com.pkostrzenski.takemine.models.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username);
    User save(User user);
}
