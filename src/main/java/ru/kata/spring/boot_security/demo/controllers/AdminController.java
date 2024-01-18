package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.MainService;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private MainService mainService;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", mainService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin/users/new")
    public String newUser(Model model) {
        List<Role> roles = mainService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "new";
    }

    @PutMapping("/admin/users")
    public String create(@ModelAttribute("user") User user) {
        mainService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/idForUpdate")
    public String showUpdateUserForm() {
        return "idForUpdate";
    }

    @PostMapping("/admin/users/idForUpdate")
    public String processUpdateUserForm(@RequestParam("id") Long id) {
        return "redirect:/admin/users/update/" + id;
    }

    @GetMapping("/admin/users/update/{id}")
    public String updateUserByID(@PathVariable("id") Long id, Model model) {
        User user = mainService.getUserById(id);
        List<Role> roles = mainService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "update";
    }

    @PatchMapping("/admin/users/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute User user) {
        mainService.update(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/idForDelete")
    public String showDeleteUserForm() {
        return "idForDelete";
    }

    @DeleteMapping("/admin/users/idForDelete")
    public String processDeleteUserForm(@RequestParam("id") Long id) {
        mainService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/userID")
    public String getIDUserForm() {
        return "userID";
    }

    @PostMapping("/admin/users/userID")
    public String postIDUserForm(@RequestParam("id") Long id) {
        return "redirect:/admin/users/userID/" + id;
    }

    @GetMapping("/admin/users/userID/{id}")
    public String getShowUserByID(@PathVariable("id") Long id, Model model) {
        User user = mainService.getUserById(id);
        List<Role> roles = mainService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "user";
    }
}