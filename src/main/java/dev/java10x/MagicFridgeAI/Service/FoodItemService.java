package dev.java10x.MagicFridgeAI.Service;

import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.DTO.FoodItemMapper;
import dev.java10x.MagicFridgeAI.Model.FoodItem;
import dev.java10x.MagicFridgeAI.Repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;
    private final FoodItemMapper foodItemMapper;

    public FoodItemService(FoodItemRepository repository, FoodItemMapper foodItemMapper) {
        this.repository = repository;
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemDTO criarFoodItem(FoodItemDTO foodItemDTO){
        FoodItem foodItem = foodItemMapper.map(foodItemDTO);
        repository.save(foodItem);
       return foodItemMapper.map(foodItem);
    }

    public FoodItemDTO deletarFoodItem(Long id){
        FoodItemDTO foodItemDeletar = listarFoodItemId(id);
        if(foodItemDeletar !=null){
            repository.deleteById(id);
            return foodItemDeletar;
        }else{
            return null;
        }
    }

    public FoodItemDTO listarFoodItemId(Long id){
        Optional<FoodItem> foodItemId = repository.findById(id);
        return foodItemId
                .map(foodItemMapper::map)
                .orElse(null);
    }

    public List<FoodItemDTO> listarFoodItems(){
        List<FoodItem> list = repository.findAll();
        return list.stream()
                .map(foodItemMapper::map)
                .collect(Collectors.toList());
    }

    public FoodItemDTO alterarFoodItem(Long id, FoodItemDTO foodItemDTO){
        if(repository.existsById(id)){
            foodItemDTO.setId(id);
            FoodItem foodItemSalvo = foodItemMapper.map(foodItemDTO);
            repository.save(foodItemSalvo);
            return foodItemMapper.map(foodItemSalvo);
        }else{
            return null;
        }
    }

}
