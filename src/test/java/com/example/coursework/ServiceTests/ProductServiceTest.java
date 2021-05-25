package com.example.coursework.ServiceTests;

import com.example.coursework.models.Product;
import com.example.coursework.repositories.ProductRepository;
import com.example.coursework.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    @Captor
    private ArgumentCaptor<Product> captor;

    @Test
    public void testAdd() throws IOException
    {
        Product product = new Product();
        product.setName("Test");
        product.setPrice(400);
        product.setImagePath("SomePath");
        product.setCategory("TestCategory");

        productService.addProduct(product);

        verify(repository).save(captor.capture());
        Product testres = captor.getValue();

        assertEquals("Test", testres.getName());
        assertEquals(400, testres.getPrice());
        assertEquals("SomePath", testres.getImagePath());
        assertEquals("TestCategory", testres.getCategory());
    }
}

