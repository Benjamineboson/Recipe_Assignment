package com.example.recipe_assignment.service;

import com.example.recipe_assignment.data.IngredientRepository;
import com.example.recipe_assignment.data.RecipeRepository;
import com.example.recipe_assignment.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CreateRecipeServiceImpl implements CreateRecipeService{

    private RecipeRepository recipeRepository;
    private CreateIngredientService createIngredientService;
    private IngredientRepository ingredientRepository;
    private CreateRecipeCategoryService createRecipeCategoryService;

    @Autowired
    public CreateRecipeServiceImpl(RecipeRepository recipeRepository, CreateIngredientService createIngredientService, IngredientRepository ingredientRepository, CreateRecipeCategoryService createRecipeCategoryService) {
        this.recipeRepository = recipeRepository;
        this.createIngredientService = createIngredientService;
        this.ingredientRepository = ingredientRepository;
        this.createRecipeCategoryService = createRecipeCategoryService;
    }

    private Recipe recipe = new Recipe();

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Recipe createAndSaveEmptyRecipe(String recipeName, RecipeInstruction recipeInstruction){
        if (hasNulls(recipeName)) throw new RuntimeException("One or several parameters are null");
        Recipe recipe = new Recipe(recipeName,recipeInstruction);
        return recipeRepository.save(recipe);
    }


    public static boolean hasNulls(Object...objects) {
        return Arrays.stream(objects)
                .anyMatch(Objects::isNull);
    }
    

    @Override
    @Transactional(rollbackFor =  RuntimeException.class)
    public Recipe createAndSave(String recipeName, String recipeInstruction,  List<String> ingredientNames, List<Measurement> measurementList, List<Double> measurementAmountList, List<String> recipeCategoryNames){
        if (hasNulls(recipeName,recipeInstruction,recipeCategoryNames,ingredientNames)) throw new RuntimeException("One or several parameters are null");
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientNames.forEach(name -> ingredientList.add(createIngredientService.createAndSave(name)));
        List<RecipeIngredient> recipeIngredientList = createRecipeIngredientList(ingredientList,measurementList,measurementAmountList);
        List<RecipeCategory> recipeCategoryList = new ArrayList<>();
        recipeCategoryNames.forEach(name -> recipeCategoryList.add(createRecipeCategoryService.create(name)));
        recipeCategoryList.forEach(recipeCategory -> recipe.addRecipeCategory(recipeCategory));
        recipe.setRecipeIngredientList(recipeIngredientList);
        recipe.setRecipeName(recipeName);
        recipe.setRecipeInstruction(new RecipeInstruction(recipeInstruction));
        return recipeRepository.save(recipe);
    }

//    private List<RecipeCategory> createRecipeCategoryList(List<String> recipeCategoryNames) {
//    return null;
//    }


    private List<RecipeIngredient> createRecipeIngredientList(List<Ingredient> ingredientList,List<Measurement> measurementList,List<Double> measurementAmountList){
        List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
        RecipeIngredient recipeIngredient;
        Ingredient[] ingredientArr = ingredientList.toArray(Ingredient[]::new);
        Measurement[] measurementArr = measurementList.toArray(Measurement[]::new);
        Double[] measurementAmountArr = measurementAmountList.toArray(Double[]::new);
        for (int i = 0; i < ingredientArr.length ; i++) {
            recipeIngredient = new RecipeIngredient(ingredientArr[i],measurementAmountArr[i],measurementArr[i]);
            recipe.addRecipeIngredient(recipeIngredient);
            recipeIngredientList.add(recipeIngredient);
        }
        return recipeIngredientList;

    }

}
