package ru.kata.spring.boot_security.demo.services.user;

import java.util.List;
import java.util.Optional;

public interface UserService<T, ID> {

    void save(T o);

    Optional<T> findById(ID id);

    void update(T o);

    void deleteById(ID id);

    List<T> findAll();
}
