package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class FirstPageController {

    @GetMapping
    public String index() {
        return "index";
    }
}
