package ru.kata.spring.boot_security.demo.services.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService<User, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User o) {
        o.setPassword(passwordEncoder.encode(o.getPassword()));
        userRepository.save(o);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void update(User o) {
        userRepository.save(o);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
}
