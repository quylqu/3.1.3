package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public void UserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return new ArrayList<>(roles);
    }
    public ru.kata.spring.boot_security.demo.entities.User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT u FROM Role u", Role.class);
        return query.getResultList();
    }

    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void update(long id, User user) {
        User userForUpdate = getUserById(id);
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setRoles(user.getRoles());
        entityManager.persist(userForUpdate);
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void delete(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}





