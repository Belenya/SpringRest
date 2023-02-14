package ru.kata.spring.boot_security.demo.services.role;

public interface RoleService<T, ID> {
    Iterable<T> findAll();
}
