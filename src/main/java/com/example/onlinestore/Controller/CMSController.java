package com.example.onlinestore.Controller;

import com.example.onlinestore.Model.Ingredient;
import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Repo.IngredientRepo;
import com.example.onlinestore.Repo.KombuchaRepo;
import com.example.onlinestore.Request.IngredientRequest;
import com.example.onlinestore.Request.KombuchaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin
public class CMSController {
    @Autowired
    IngredientRepo repo;
    @Autowired
    KombuchaRepo kombuchaRepo;
    @GetMapping
    public String getRequest(){
        return "REGUEST";
    }
    @PostMapping("/ingredients")
    public ResponseEntity<String> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequest.getName());
        repo.save(ingredient);
        return ResponseEntity.ok("Ингредиент успешно добавлен.");
    }
    @PostMapping("/kombucha")
    public ResponseEntity<String> createKombucha(@RequestBody KombuchaRequest kombuchaRequest) {
        Kombucha kombucha = new Kombucha();
        kombucha.setName(kombuchaRequest.getName());
        kombucha.setPrice(kombuchaRequest.getPrice());
        kombucha.setImg(kombuchaRequest.getImg());
        kombucha.setCategory(kombuchaRequest.getCategory());

        List<Ingredient> ingredients = new ArrayList<>();
        for (String ingredientName : kombuchaRequest.getIngredients()) {
            Ingredient ingredient = repo.findByName(ingredientName);
            if (ingredient == null) {
                // Обработка ситуации, когда указанный ингредиент не существует
                return ResponseEntity.badRequest().body("Ингредиент '" + ingredientName + "' не найден в справочнике.");
            }
            ingredients.add(ingredient);
        }

        kombucha.setIngredients(ingredients);

        kombuchaRepo.save(kombucha);

        return ResponseEntity.ok("Напиток комбуча успешно создан.");
    }
}
