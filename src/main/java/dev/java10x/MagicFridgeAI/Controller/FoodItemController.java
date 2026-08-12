package dev.java10x.MagicFridgeAI.Controller;

import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.Model.FoodItem;
import dev.java10x.MagicFridgeAI.Service.FoodItemService;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @PostMapping("criar")
    public ResponseEntity<FoodItemDTO> criarFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO foodItemSalvo = service.criarFoodItem(foodItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemSalvo);
    }

    @DeleteMapping("deletar")
    public ResponseEntity<String> deletarFoodItem(@PathVariable Long id){
        FoodItemDTO foodItemDeletar = service.deletarFoodItem(id);
        if(foodItemDeletar!=null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item não encontrado para deleção.");
        }
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<?> listarFoodItemId(@PathVariable Long id){
        FoodItemDTO foodItemId = service.listarFoodItemId(id);
        if(foodItemId!=null){
            return ResponseEntity.ok().body(foodItemId);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item não encontrado");
        }
    }

    @GetMapping("listar")
    public ResponseEntity<List<FoodItemDTO>>listarFoodItems(){
        List<FoodItemDTO> foodItems = service.listarFoodItems();
        return ResponseEntity.ok(foodItems);
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<?> alterarFoodItem(@PathVariable Long id,@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO foodItemAlterado = service.alterarFoodItem(id,foodItemDTO);
        if(foodItemAlterado!=null){
            return ResponseEntity.ok().body(foodItemAlterado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item não encontrado para alteração.");
        }
    }
}
