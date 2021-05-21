package com.example.coursework.controllers;

import com.example.coursework.models.Product;
import com.example.coursework.models.User;
import com.example.coursework.services.ProductService;
import com.example.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ProductService productService;

    @GetMapping("/addToCart/{productID}")
    public String addToCart(Principal principal, @PathVariable("productID") Long productID)
    {
        User user = (User) service.loadUserByUsername(principal.getName());
        service.addToCart(productID, user);
        return "redirect:/user/cart";
    }

    @PostMapping("/removeFromCart/{productID}")
    public String removeFromCart(Principal principal, @PathVariable("productID") Long productID)
    {
        User user = (User) service.loadUserByUsername(principal.getName());

        service.removeFromCart(productID, user);

        return "redirect:/user/cart";
    }

    @GetMapping("/cart")
    public String show(Model model, Principal principal)
    {
        User user = (User) service.loadUserByUsername(principal.getName());

        List<Product> products = user.getCart();
        float total = 0;
        model.addAttribute("products", products);
        for (Product product :
                products) {
            total+=product.getPrice();
        }
        model.addAttribute("total", Float.toString(total));

        return "user/cart";
    }
}
