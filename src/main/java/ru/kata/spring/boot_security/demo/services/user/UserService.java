package ru.kata.spring.boot_security.demo.services.user;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    void save(User user);

    Optional<User> findById(Long id);

    void update(User user);

    void deleteById(Long id);

    List<User> findAll();
}
