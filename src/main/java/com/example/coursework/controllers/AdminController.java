package com.example.coursework.controllers;

import com.example.coursework.models.Product;
import com.example.coursework.services.ProductService;
import com.example.coursework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        boolean success = false;
        model.addAttribute("success", success);
        return "admin/addProduct";
    }
    @PostMapping("/addProduct")
    public String addProduct(Product product) throws IOException
    {
        productService.addProduct(product);
        return "admin/addProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/products";

    }

    @GetMapping("/deleteUser")
    public String deleteUser()
    {
        return "admin/deleteUser";
    }
    @PostMapping("/deleteUser")
    public @ResponseBody String deleteUser(@RequestParam("username") String username)
    {
        boolean success = userService.deleteUser(username);
        if(success)
            return "Deleted successfully!";
        else
            return "Error!";
    }

}
