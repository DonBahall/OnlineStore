package com.example.onlinestore.Controller;

import com.example.onlinestore.Model.Kombucha;
import com.example.onlinestore.Repo.KombuchaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@CrossOrigin
public class WebContoller {
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
    @GetMapping("/search")
    public ResponseEntity<List<Kombucha>> searchKombucha(@RequestParam(value = "query") String query) {
        // Проверяем, является ли запрос кириллическими символами
        if (isCyrillic(query)) {
            return ResponseEntity.badRequest().body(Collections.singletonList(new Kombucha()));
        }

        // Удаляем лишние пробелы и проверяем, является ли запрос пустым
        query = query.trim();
        if (query.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        // Приводим запрос к нижнему регистру
        query = query.toLowerCase();

        // Выполняем поиск комбучи по названию, ингредиентам или категории
        List<Kombucha> results = kombuchaRepository.findByNameOrCategory(query,"");

        return ResponseEntity.ok(results);
    }

    private boolean isCyrillic(String text) {
        return text.matches(".*\\p{InCyrillic}.*");
    }
}
