package com.example.coursework.ServiceTests;
import com.example.coursework.models.Product;
import com.example.coursework.models.User;
import com.example.coursework.repositories.ProductRepository;
import com.example.coursework.repositories.UserRepository;
import com.example.coursework.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;
    @Captor
    ArgumentCaptor<User> captor;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testReg()
    {
        User user = new User();
        user.setUsername("Testname");
        user.setEmail("Email");
        user.setPassword("123");

        service.register(user);

        verify(repository).save(captor.capture());
        User testres = captor.getValue();
        assertEquals("Testname", testres.getUsername());
        assertEquals("Email", testres.getEmail());
        assertTrue(passwordEncoder.matches("123", testres.getPassword()));
        assertEquals("USER", testres.getRole());
    }

    @Test
    public void loadUserByUsername()
    {
        User user = new User();
        user.setUsername("Testname");
        user.setEmail("Email");
        user.setPassword("123");

        when(repository.findByUsername("Testname")).thenReturn(user);

        User testres = (User) service.loadUserByUsername("Testname");

        assertEquals("Testname", testres.getUsername());
        assertEquals("Email", testres.getEmail());
        assertEquals("123", testres.getPassword());
    }
}
