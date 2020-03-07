package com.example.recipe_assignment.service;

import com.example.recipe_assignment.entity.Measurement;
import com.example.recipe_assignment.entity.Recipe;
import com.example.recipe_assignment.entity.RecipeInstruction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreateRecipeService {
    @Transactional(rollbackFor = RuntimeException.class)
    Recipe createAndSaveEmptyRecipe (String recipeName, RecipeInstruction recipeInstruction);
    @Transactional
    Recipe createAndSave(String recipeName, String recipeInstruction, List<String> ingredientNames, List<Measurement> measurementList, List<Double> measurementAmountList, List<String> recipeCategoryNames);
}
