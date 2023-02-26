package ru.kata.spring.boot_security.demo.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.user.UserService;


@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @GetMapping
    public User getPrincipal(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

}
