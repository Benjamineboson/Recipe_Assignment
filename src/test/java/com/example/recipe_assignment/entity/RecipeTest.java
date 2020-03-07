package com.example.recipe_assignment.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    private Recipe recipe;
    private RecipeCategory recipeCategory;
    private RecipeCategory recipeCategory2;
    private RecipeIngredient recipeIngredient;
    private RecipeIngredient recipeIngredient2;

    @BeforeEach
    void setUp() {
        recipe = new Recipe("Test recipe");
        recipeCategory = new RecipeCategory("Test category");
        recipeCategory2 = new RecipeCategory("Test category2");
        recipeIngredient = new RecipeIngredient(new Ingredient("Test ingredient"));
        recipeIngredient2 = new RecipeIngredient(new Ingredient("Test ingredient2"));
    }

    @Test
    public void addRecipeCategory_test(){
        recipe.addRecipeCategory(recipeCategory);
        recipe.addRecipeCategory(recipeCategory2);

        assertNotNull(recipe.getRecipeCategoryList());
        assertEquals(2,recipe.getRecipeCategoryList().size());
    }

    @Test
    public void removeRecipeCategory_test(){
        recipe.addRecipeCategory(recipeCategory);
        recipe.addRecipeCategory(recipeCategory2);
        recipe.removeRecipeCategory(recipeCategory);

        assertNotNull(recipe.getRecipeCategoryList());
        assertEquals(1,recipe.getRecipeCategoryList().size());
    }

    @Test
    public void addRecipeIngredient_test(){
        recipe.addRecipeIngredient(recipeIngredient);
        recipe.addRecipeIngredient(recipeIngredient2);

        assertNotNull(recipe.getRecipeIngredientList());
        assertEquals(2,recipe.getRecipeIngredientList().size());
    }

    @Test
    public void removeRecipeIngredient_test(){
        recipe.addRecipeIngredient(recipeIngredient);
        recipe.addRecipeIngredient(recipeIngredient2);
        recipe.removeRecipeIngredient(recipeIngredient);

        assertNotNull(recipe.getRecipeIngredientList());
        assertEquals(1,recipe.getRecipeIngredientList().size());
    }
}
