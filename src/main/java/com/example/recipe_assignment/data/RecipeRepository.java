package com.example.recipe_assignment.data;

import com.example.recipe_assignment.entity.Recipe;
import com.example.recipe_assignment.entity.RecipeCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe,Integer> {
    Optional<Recipe> findByRecipeName(String name);
    @Query("SELECT r from Recipe r WHERE r.recipeName LIKE %:recipeName%")
    List<Recipe> findRecipeByNameContaining(@Param("recipeName") String recipeName);
    @Query("SELECT r FROM Recipe r JOIN FETCH r.recipeIngredientList recipeIngredient WHERE recipeIngredient.ingredient.ingredientName = :ingredientName")
    List<Recipe> findRecipeByIngredient(@Param("ingredientName") String ingredientName);
    @Query("SELECT r FROM Recipe r JOIN FETCH r.recipeCategoryList recipeCategory WHERE recipeCategory.recipeCategoryName = :categoryName")
    List<Recipe> findRecipeByCategory(@Param("categoryName")String categoryName);
    @Query("SELECT r FROM Recipe r JOIN FETCH r.recipeCategoryList recipeCategory WHERE recipeCategory.recipeCategoryName IN :categories")
    List<Recipe> findRecipeByCategories(@Param("categories")List<String> categories);
}
