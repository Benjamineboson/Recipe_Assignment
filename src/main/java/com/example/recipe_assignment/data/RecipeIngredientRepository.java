package com.example.recipe_assignment.data;

import com.example.recipe_assignment.entity.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient,String> {
}
