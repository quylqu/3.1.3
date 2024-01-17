//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kata.spring.boot_security.demo.entities.User;
//import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
//
//import java.security.Principal;
//
//@Controller
//public class MainController {
//    private final UserServiceImpl userServiceImpl;
//
//    @Autowired
//    public MainController(UserServiceImpl userServiceImpl) {
//        this.userServiceImpl = userServiceImpl;
//    }
//
//    @GetMapping("/security/")
//    public String homePage() {
//        return "home";
//    }
//
//    @GetMapping("/security/authenticated")
//    public String pageForAuthenticatedUser(Principal principal) {
//        User user = userServiceImpl.findByUsername(principal.getName());
//        return "secured part of web service: " + user.getUsername() + " " + user.getEmail();
//    }
//
//    @GetMapping("/security/read_profile")
//    public String pageForReadProfile() {
//        return "read profile page";
//    }
//
//    @GetMapping("/security/only_for_admins")
//    public String pageForOnlyForAdmins() {
//        return "admins page";
//    }
//
//    @GetMapping("/security/admin/users")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users", userServiceImpl.getAllUsers());
//        return "users";
//    }
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//}
