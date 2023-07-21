package com.example.onlinestore.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kombucha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private String img;
    private String category;
    @ManyToMany
    @JoinTable(name = "kombucha_ingredient",
            joinColumns = @JoinColumn(name = "kombucha_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
    @Enumerated(EnumType.STRING)
    private Packaging packaging;
    private Double weight;
    private Double rating;
    private Double amount;
    @ManyToMany
    @JoinTable(name = "synonyms",
            joinColumns = @JoinColumn(name = "kombucha_id"),
            inverseJoinColumns = @JoinColumn(name = "synonym_id"))
    private List<Synonym> synonyms;
    private String comment;
    private Date shelf_life;
    private String storage_conditions;
    private String allergens;
    private Double energy_value;

    public Kombucha(String name, Double price, String img, String category,
                    List<Ingredient> ingredients, Packaging packaging, Double weight,
                    Double rating, Double amount, List<Synonym> synonyms, String comment,
                    Date shelf_life, String storage_conditions, String allergens, Double energy_value) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.category = category;
        this.ingredients = ingredients;
        this.packaging = packaging;
        this.weight = weight;
        this.rating = rating;
        this.amount = amount;
        this.synonyms = synonyms;
        this.comment = comment;
        this.shelf_life = shelf_life;
        this.storage_conditions = storage_conditions;
        this.allergens = allergens;
        this.energy_value = energy_value;
    }
}
