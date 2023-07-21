package com.example.onlinestore.Controller;

import com.example.onlinestore.Model.Ingredient;
import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Model.Synonym;
import com.example.onlinestore.Repo.IngredientRepo;
import com.example.onlinestore.Repo.KombuchaRepo;
import com.example.onlinestore.Repo.SynonymRepo;
import com.example.onlinestore.Request.IngredientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin
public class CMSController {
    private final IngredientRepo repo;
    private final KombuchaRepo kombuchaRepo;
    private final SynonymRepo synonymRepo;

    public CMSController(IngredientRepo repo, KombuchaRepo kombuchaRepo, SynonymRepo synonymRepo) {
        this.repo = repo;
        this.kombuchaRepo = kombuchaRepo;
        this.synonymRepo = synonymRepo;
    }
    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = repo.findAll();
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/synonyms")
    public ResponseEntity<List<Synonym>> getAllSynonyms() {
        List<Synonym> synonyms = synonymRepo.findAll();
        return ResponseEntity.ok(synonyms);
    }
    @PostMapping("/ingredients")
    public ResponseEntity<String> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequest.getName());
        repo.save(ingredient);
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
