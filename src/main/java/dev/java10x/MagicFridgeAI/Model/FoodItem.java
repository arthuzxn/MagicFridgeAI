package dev.java10x.MagicFridgeAI.Model;

import dev.java10x.MagicFridgeAI.Enums.CategoriaFoodItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_food_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private CategoriaFoodItem categoriaFoodItem;
    private Integer quantidade;
    @Column(name="DATA_VALIDADE")
    private LocalDate dataValidade;


}
