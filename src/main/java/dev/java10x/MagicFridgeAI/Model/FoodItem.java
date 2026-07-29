package dev.java10x.MagicFridgeAI.Model;

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
    private String categoria;
    private Integer quantidade;
    @Column(name="DATA_VALIDADE")
    private LocalDate dataValidade;


}
