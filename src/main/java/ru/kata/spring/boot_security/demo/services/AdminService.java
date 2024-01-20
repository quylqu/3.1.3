package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    List<Role> getAllRoles();

    void save(User user);

    void update(long id, User user);

    void delete(long id);

    User getUserById(long id);
}
