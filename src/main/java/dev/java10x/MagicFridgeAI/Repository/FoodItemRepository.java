package dev.java10x.MagicFridgeAI.Repository;

import dev.java10x.MagicFridgeAI.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
