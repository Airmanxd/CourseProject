package com.example.coursework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private String category;
    @JsonIgnore
    private String imagePath;
    @ManyToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<User> userList;

    public void addUser(User user)
    {
        userList.add(user);
    }

    public void removeUser(User user)
    {
        userList.remove(user);
    }
}
