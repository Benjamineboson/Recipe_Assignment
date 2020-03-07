package com.example.recipe_assignment.service;

import com.example.recipe_assignment.entity.Ingredient;
import org.springframework.transaction.annotation.Transactional;

public interface CreateIngredientService {
    @Transactional(rollbackFor = RuntimeException.class)
    Ingredient createAndSave(String ingredientName);
}
