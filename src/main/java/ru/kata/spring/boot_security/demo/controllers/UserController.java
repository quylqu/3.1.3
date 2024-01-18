package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.MainService;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private MainService mainService;

    @GetMapping("/user")
    public String pageForAuthenticatedUser(Model model, Principal principal) {
        User user = mainService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
