package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.role.RoleService;
import ru.kata.spring.boot_security.demo.services.user.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String admin(@ModelAttribute("newUser") User user,
                        @ModelAttribute("editedUser") User editedUser,
                        Authentication authentication,
                        Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("roles", roleService.findAll());
        return "admin/admin";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("editedUser") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        userService.deleteById(Long.parseLong(id));
        return "redirect:/admin";
    }
}
