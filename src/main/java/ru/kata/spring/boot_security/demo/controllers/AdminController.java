package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.user.UserService;
import ru.kata.spring.boot_security.demo.services.user.UserServiceImpl;
import ru.kata.spring.boot_security.demo.utils.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService<User, Long> userService;
    private final UserService<Role, Long> roleService;
    private final UserValidator userValidator;

    public AdminController(UserServiceImpl userService, PasswordEncoder passwordEncoder, UserService<Role, Long> roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }


    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/admin/adminPanel";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findById(Long.parseLong(id)).orElse(new User()));
        return "/admin/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "/admin/new";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/admin/new";
        }
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") String id) {
        model.addAttribute("user", userService.findById(Long.parseLong(id)).orElse(new User()));
        model.addAttribute("roles", roleService.findAll());
        return "/admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/edit";
        }
        if (user.getPassword().isEmpty()) {
            User user = userService.findById(user.getId()).;

        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        userService.deleteById(Long.parseLong(id));
        return "redirect:/admin";
    }
}
