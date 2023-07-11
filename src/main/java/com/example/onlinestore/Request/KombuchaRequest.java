package com.example.onlinestore.Request;

import com.example.onlinestore.Model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KombuchaRequest {
    private String name;
    private Double price;
    private String img;
    private String category;
    private List<String> ingredients;
}
