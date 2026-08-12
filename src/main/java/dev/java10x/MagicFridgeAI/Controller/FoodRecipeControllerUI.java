package dev.java10x.MagicFridgeAI.Controller;

import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.Model.FoodItem;
import dev.java10x.MagicFridgeAI.Service.FoodItemService;
import dev.java10x.MagicFridgeAI.Service.GeminiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("food-recipe/ui")

public class FoodRecipeControllerUI {

    private final GeminiService geminiService;
    private final FoodItemService foodItemService;

    public FoodRecipeControllerUI(GeminiService geminiService, FoodItemService foodItemService) {
        this.geminiService = geminiService;
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public String listarFoodItems(Model model){
        List<FoodItemDTO> listFoodItems =foodItemService.listarFoodItems();
        model.addAllAttributes(Map.of("listFoodItems", listFoodItems, "foodItem", new FoodItemDTO()));
        return "ListarFoodItems";
    }

    @PostMapping("/save")
    public String saveFoodItem(@ModelAttribute FoodItemDTO foodItemDTO){
        foodItemService.criarFoodItem(foodItemDTO);
        return "redirect:/food-recipe/ui";
    }
    @GetMapping("/delete/{id}")
    public String deleteFoodItem(@PathVariable Long id){
        foodItemService.deletarFoodItem(id);
        return "redirect:/food-recipe/ui";
    }

    @PostMapping("/generateRecipe")
    public String generateRecipe(Model model) {
        List<FoodItemDTO> list = foodItemService.listarFoodItems();
        String responseGemini = geminiService.generateRecipe(list).block();
        model.addAllAttributes(Map.of(
                "listFoodItems", list,
                "foodItem", new FoodItemDTO(),
                "responseGemini", responseGemini));
        return "ListarFoodItems";

    }

}
