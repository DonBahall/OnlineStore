package com.example.onlinestore.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Kombucha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private String img;
    private String category;
    private Double displacement;
    @ManyToMany
    @JoinTable(name = "kombucha_ingredient",
            joinColumns = @JoinColumn(name = "kombucha_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
}