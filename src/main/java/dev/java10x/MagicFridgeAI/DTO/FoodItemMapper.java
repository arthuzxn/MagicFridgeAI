package dev.java10x.MagicFridgeAI.DTO;

import dev.java10x.MagicFridgeAI.Model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodItemMapper {

    public FoodItemDTO map(FoodItem foodItem){
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setNome(foodItem.getNome());
        foodItemDTO.setCategoriaFoodItem(foodItem.getCategoriaFoodItem());
        foodItemDTO.setQuantidade(foodItem.getQuantidade());
        foodItemDTO.setDataValidade(foodItem.getDataValidade());
        return foodItemDTO;
    }

    public FoodItem map(FoodItemDTO foodItemDTO){
        FoodItem foodItem = new FoodItem();
        foodItem.setId(foodItemDTO.getId());
        foodItem.setNome(foodItemDTO.getNome());
        foodItem.setCategoriaFoodItem(foodItemDTO.getCategoriaFoodItem());
        foodItem.setQuantidade(foodItemDTO.getQuantidade());
        foodItem.setDataValidade(foodItemDTO.getDataValidade());
        return foodItem;
    }
}
