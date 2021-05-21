package com.example.coursework.controllers;

import com.example.coursework.models.User;
import com.example.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("index")
    public String home_page(){ return "index";}
    @GetMapping("")
    public String index(){ return "index";}

    @GetMapping("login")
    public String signIn() {
        return "login";
    }

    @PostMapping("login")
    public String login(){ return "index";}

    @GetMapping("registration")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("registration")
    public String reg(User user) {
        userService.register(user);
        return "redirect:/products";
    }

    @GetMapping("error")
    public String error(){
        return "error";
    }
}
