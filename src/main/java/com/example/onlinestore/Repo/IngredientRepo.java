package com.example.onlinestore.Repo;

import com.example.onlinestore.Model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient,Long> {
}
