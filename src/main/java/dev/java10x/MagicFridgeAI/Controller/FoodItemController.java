package dev.java10x.MagicFridgeAI.Controller;

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
    public ResponseEntity<FoodItem> criarFoodItem(@RequestBody FoodItem foodItem){
        FoodItem foodItemSalvo = service.criarFoodItem(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemSalvo);
    }

    @DeleteMapping("deletar")
    public ResponseEntity<String> deletarFoodItem(@PathVariable Long id){
        FoodItem foodItemDeletar = service.deletarFoodItem(id);
        if(foodItemDeletar!=null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Food item deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item não encontrado para deleção.");
        }
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<?> listarFoodItemId(@PathVariable Long id){
        FoodItem foodItemId = service.listarFoodItemId(id);
        if(foodItemId!=null){
            return ResponseEntity.ok().body(foodItemId);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item não encontrado");
        }
    }

    @GetMapping("listar")
    public ResponseEntity<List<FoodItem>>listarFoodItems(){
        List<FoodItem> foodItems = service.listarFoodItems();
        return ResponseEntity.ok(foodItems);
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<?> alterarFoodItem(Long id, FoodItem foodItem){
        FoodItem foodItemAlterado = service.alterarFoodItem(id,foodItem);
        if(foodItemAlterado!=null){
            return ResponseEntity.ok().body(foodItemAlterado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item não encontrado para alteração.");
        }
    }
}
