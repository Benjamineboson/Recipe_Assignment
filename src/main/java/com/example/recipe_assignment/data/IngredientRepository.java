package com.example.recipe_assignment.data;

import com.example.recipe_assignment.entity.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface IngredientRepository extends CrudRepository<Ingredient,Integer> {
    Optional<Ingredient> findByIngredientName(String ingredientName);
    @Query("SELECT i from Ingredient i WHERE i.ingredientName LIKE %:ingredientName% ")
    List<Ingredient> findIngredientByNameContaining(String ingredientName);

}
