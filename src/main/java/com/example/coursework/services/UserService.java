package com.example.coursework.services;

import com.example.coursework.models.Product;
import com.example.coursework.models.User;
import com.example.coursework.repositories.ProductRepository;
import com.example.coursework.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean register(User request){
        if (userRepository.findByUsername(request.getUsername()) != null)
            return false;

        if(request.getUsername().equals("Admin"))
            request.setRole("ADMIN");
        else
            request.setRole("USER");

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(request);
        log.info("Registering user " + request.getUsername() + " email: " + request.getEmail());
        return true;
    }

    public User login(String username, String password)
    {
        log.info("User " + username + "is logging in");
        User user = userRepository.findByUsername(username);
        if(user != null && passwordEncoder.matches(password, user.getPassword()))
            return user;
        else
            return null;
    }

    public boolean addToCart(Long id, User user){
        log.info(String.format("Adding product %s to %s's cart", id, user.getUsername()));
        Product product = productRepository.findByProductId(id);
        if(product == null)
            return false;
        product.addUser(user);
        user.add_to_cart(product);

        productRepository.save(product);
        userRepository.save(user);
        return true;
    }

    public boolean removeFromCart(Long id, User user)
    {
        log.info(String.format("Removing product %s from %s's cart", id, user.getUsername()));
        Product product = productRepository.findByProductId(id);
        if(product == null)
            return false;
        user.remove_from_cart(product);
        product.removeUser(user);
        productRepository.save(product);
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(String username)
    {
        User user = userRepository.findByUsername(username);
        if(user == null)
            return false;
        userRepository.delete(user);
        return true;
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);
        return user;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
