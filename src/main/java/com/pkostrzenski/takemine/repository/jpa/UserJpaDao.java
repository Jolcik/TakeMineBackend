package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.User;
import com.pkostrzenski.takemine.repository.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("UserJPA")
public class UserJpaDao implements UserDao {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username) {
        return userJpaRepository.findByUsernameOrEmail(username);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.saveAndFlush(user);
    }
}
