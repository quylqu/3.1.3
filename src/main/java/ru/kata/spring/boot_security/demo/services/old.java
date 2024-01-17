//package ru.kata.spring.boot_security.demo.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
////import ru.kata.spring.boot_security.demo.dao.UserDao;
//import ru.kata.spring.boot_security.demo.entities.Role;
//import ru.kata.spring.boot_security.demo.entities.User;
//import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
//import ru.kata.spring.boot_security.demo.repositories.UserRepository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class MainSevice implements UserDetailsService {
//    @PersistenceContext
//    private EntityManager entityManager;
//    private UserRepository userRepository;
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
////    @Autowired
////    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
//    }
//
////    public User findUserById(Long userId) {
////        Optional<User> userFromDb = userRepository.findById(userId);
////        return userFromDb.orElse(new User());
////    }
//
//    public List<User> allUsers() {
//        return userRepository.findAll();
//    }
//    //    @Transactional
////    public boolean saveUser(User user) {
////        User userFromDB = userRepository.findByUsername(user.getUsername());
////
////        if (userFromDB != null) {
////            return false;
////        }
////
////        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
////        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        userRepository.save(user);
////        return true;
////    }
//    @Transactional
//    public boolean deleteUser(Long userId) {
//        if (userRepository.findById(userId).isPresent()) {
//            userRepository.deleteById(userId);
//            return true;
//        }
//        return false;
//    }
//    public List<User> usergtList(Long idMin) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
//                .setParameter("paramId", idMin).getResultList();
//    }
//
//    public List<User> getAllUsers() {
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
//        return query.getResultList();
//    }
//
//    public ru.kata.spring.boot_security.demo.entities.User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }
//}
//


//package ru.kata.spring.boot_security.demo.services;
//
//import ru.kata.spring.boot_security.demo.entities.User;
//
//import java.util.List;
//
//public interface UserService {
//
//    List<User> getAllUsers();
//
////    User getUserById(long id);
////
////    void save(User user);
////
////    void update(long id, User user);
////
////    void delete(long id);
//
//}