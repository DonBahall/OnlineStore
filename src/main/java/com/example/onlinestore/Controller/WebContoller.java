package com.example.onlinestore.Controller;

import com.example.onlinestore.Model.Ingredient;
import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Repo.IngredientRepo;
import com.example.onlinestore.Repo.KombuchaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@CrossOrigin
public class WebContoller {
    private final IngredientRepo ingredientRepo;
    private final KombuchaRepo kombuchaRepository;
    @GetMapping("/category")
    public Set<String> getCategory(){
        List<Kombucha> kombuchas = kombuchaRepository.findAll();
        Set<String> categories = new HashSet<>();

        for (Kombucha kombucha : kombuchas) {
            categories.add(kombucha.getCategory());
        }

        return categories;
    }
    @GetMapping("/kombucha")
    public List<Kombucha> getKombucha(){
        return kombuchaRepository.findAll();
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        return ResponseEntity.ok(ingredients);
    }
    @GetMapping("/kombucha/{id}")
    public Kombucha getKombuchaById(@PathVariable Long id){
        return kombuchaRepository.findById(id).orElse(null);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Kombucha>> searchKombucha(@RequestParam String query) {
        if (!isLatin(query)) {
            return ResponseEntity.badRequest().body(Collections.singletonList(new Kombucha()));
        }

        query = query.trim().toLowerCase();

        // Выполняем поиск комбучи по части строки в названии
        List<Kombucha> resultsByName = kombuchaRepository.findByNameContainingIgnoreCase(query);

        // Выполняем поиск комбучи по части строки в полях связанных сущностей Synonym
        List<Kombucha> resultsBySynonymName = kombuchaRepository.findBySynonymsNameContainingIgnoreCase(query);

        // Объединяем результаты из обоих запросов (если нужно)
        List<Kombucha> results = new ArrayList<>();
        results.addAll(resultsByName);
        results.addAll(resultsBySynonymName);

        return ResponseEntity.ok(results);
    }

    public boolean isLatin(String input) {
        // Шаблон для проверки латинских символов
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        return pattern.matcher(input).matches();
    }
}
