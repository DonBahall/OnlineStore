package com.example.onlinestore.Controller;

import com.example.onlinestore.Model.Ingredient;
import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Model.Synonym;
import com.example.onlinestore.Repo.IngredientRepo;
import com.example.onlinestore.Repo.KombuchaRepo;
import com.example.onlinestore.Repo.SynonymRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin
public class CMSController {
    private final IngredientRepo ingredientRepo;
    private final KombuchaRepo kombuchaRepo;
    private final SynonymRepo synonymRepo;

    public CMSController(IngredientRepo ingredientRepo, KombuchaRepo kombuchaRepo, SynonymRepo synonymRepo) {
        this.ingredientRepo = ingredientRepo;
        this.kombuchaRepo = kombuchaRepo;
        this.synonymRepo = synonymRepo;
    }
    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/synonyms")
    public ResponseEntity<List<Synonym>> getAllSynonyms() {
        List<Synonym> synonyms = synonymRepo.findAll();
        return ResponseEntity.ok(synonyms);
    }
    @PostMapping("/synonyms")
    public ResponseEntity<String> addSynonyms(@RequestBody Synonym synonym) {
        synonymRepo.save(synonym);
        return ResponseEntity.ok("Synonym added successfully.");
    }
    @PostMapping("/ingredients")
    public ResponseEntity<String> addIngredient(@RequestBody Ingredient ingredient) {
        ingredientRepo.save(ingredient);
        return ResponseEntity.ok("Ingredient added successfully.");
    }
    @PostMapping("/kombucha")
    public ResponseEntity<Kombucha> createKombucha(@RequestBody Kombucha kombucha) {
        List<Ingredient> ingredients = kombucha.getIngredients();
        List<Synonym> synonyms = kombucha.getSynonyms();
        kombucha.setIngredients(ingredients);
        kombucha.setSynonyms(synonyms);
        Kombucha savedKombucha = kombuchaRepo.save(kombucha);
        return ResponseEntity.ok(savedKombucha);
    }
}
