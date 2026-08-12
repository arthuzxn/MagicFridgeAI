package dev.java10x.MagicFridgeAI.Controller;


import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.Model.FoodItem;
import dev.java10x.MagicFridgeAI.Repository.FoodItemRepository;
import dev.java10x.MagicFridgeAI.Service.FoodItemService;
import dev.java10x.MagicFridgeAI.Service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private final GeminiService geminiService;
    private final FoodItemService foodItemService;

    public RecipeController(GeminiService geminiService, FoodItemService foodItemService) {
        this.geminiService = geminiService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(){
        List<FoodItemDTO> list = foodItemService.listarFoodItems();
        return geminiService.generateRecipe(list)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
