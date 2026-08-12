package dev.java10x.MagicFridgeAI.DTO;

import dev.java10x.MagicFridgeAI.Enums.CategoriaFoodItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItemDTO {

    private Long id;
    private String nome;
    private CategoriaFoodItem categoriaFoodItem;
    private Integer quantidade;
    private LocalDate dataValidade;
}
