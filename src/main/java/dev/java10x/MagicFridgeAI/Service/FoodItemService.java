package dev.java10x.MagicFridgeAI.Service;

import dev.java10x.MagicFridgeAI.Model.FoodItem;
import dev.java10x.MagicFridgeAI.Repository.FoodItemRepository;
import org.hibernate.metamodel.mapping.ForeignKeyDescriptor;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItem criarFoodItem(FoodItem foodItem){
       return repository.save(foodItem);
    }

    public FoodItem deletarFoodItem(Long id){
        FoodItem foodItemDeletar = listarFoodItemId(id);
        if(foodItemDeletar !=null){
            repository.deleteById(id);
            return foodItemDeletar;
        }else{
            return null;
        }
    }

    public FoodItem listarFoodItemId(Long id){
        Optional<FoodItem> foodItemId = repository.findById(id);
        return foodItemId
                .orElse(null);
    }

    public List<FoodItem> listarFoodItems(){
        return repository.findAll();
    }

    public FoodItem alterarFoodItem(Long id, FoodItem foodItem){
        if(repository.existsById(id)){
            foodItem.setId(id);
            return repository.save(foodItem);
        }else{
            return null;
        }
    }

}
