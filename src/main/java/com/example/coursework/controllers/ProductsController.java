package com.example.coursework.controllers;

import com.example.coursework.models.Product;
import com.example.coursework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService service;

    @GetMapping()
    public String initial(Model model) throws IOException
    {
        List<Product> products = service.getAll();
        model.addAttribute("products", products);
        return "products";
    }
    @PostMapping("/categorized")
    public String categorized(Model model,@ModelAttribute("category") String category) throws IOException
    {
        List<Product> products = service.getAll();
        if(!category.equals("All")){
            List<Product> result = new ArrayList<Product>();
            for (Product product :
                    products) {
                if(product.getCategory().equals(category))
                    result.add(product);
            }
            model.addAttribute("products", result);
        }
        else
            model.addAttribute("products", products);
        return "products";
    }
}
