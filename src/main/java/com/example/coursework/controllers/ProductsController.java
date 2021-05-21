package com.example.coursework.controllers;

import com.example.coursework.models.Product;
import com.example.coursework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductsController {
    @Autowired
    private ProductService service;

    @GetMapping("products")
    public String initial(Model model) throws IOException
    {
        List<Product> products = service.getAll();
        model.addAttribute("products", products);
        return "products";
    }
}
