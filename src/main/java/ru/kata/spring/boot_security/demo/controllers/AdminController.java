package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.MainService;

import java.security.Principal;

@Controller
public class AdminController {
    @Autowired
    private MainService mainService;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", mainService.getAllUsers());
        return "users";
    }
//
//    @PostMapping("/admin/users")
//    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
//                             @RequestParam(required = true, defaultValue = "") String action,
//                             Model model) {
//        if (action.equals("delete")) {
//            userService.deleteUser(userId);
//        }
//        return "redirect:/admin/users";
//    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }

//    @PutMapping()
//    public String create(@ModelAttribute("user") User user) {
//        mainService.save(user);
//        return "redirect:/users";
//    }

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
        model.addAttribute("user", user);
        return "update";
    }

    @PatchMapping("/admin/users/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute User user, Model model) {
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
}