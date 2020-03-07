package com.example.recipe_assignment;

import com.example.recipe_assignment.data.IngredientRepository;
import com.example.recipe_assignment.data.RecipeIngredientRepository;
import com.example.recipe_assignment.data.RecipeRepository;
import com.example.recipe_assignment.entity.*;
import com.example.recipe_assignment.service.CreateRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandLine implements CommandLineRunner {



    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private CreateRecipeService createRecipeService;

    @Autowired
    public CommandLine(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, CreateRecipeService createRecipeService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.createRecipeService = createRecipeService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        Ingredient bacon = new Ingredient("Bacon");
//        Ingredient egg = new Ingredient("Egg");
//        Ingredient flower = new Ingredient("Flower");
//        Ingredient milk = new Ingredient("Milk");
//        RecipeCategory swedish = new RecipeCategory("Swedish food");
//        RecipeCategory fat = new RecipeCategory("Fatty food");
//        RecipeInstruction recipeInstruction2 = new RecipeInstruction("COOKING INSTRUCTIONS");
//        RecipeIngredient riBacon2 = new RecipeIngredient(bacon,200, Measurement.G);
//        RecipeIngredient riEgg2 = new RecipeIngredient(egg,2, Measurement.ST);
//        RecipeIngredient riFlower2 = new RecipeIngredient(flower,2,Measurement.DL);
//        RecipeIngredient riMilk2 = new RecipeIngredient(milk,4,Measurement.DL);
//        Recipe recipe2 = new Recipe("Ägg och bacon",recipeInstruction2);
//        recipe2.addRecipeCategory(swedish);
//        recipe2.addRecipeIngredient(riBacon2);
//        recipe2.addRecipeIngredient(riEgg2);
//        recipe2.addRecipeIngredient(riFlower2);
//        recipe2.addRecipeIngredient(riMilk2);
//        recipe2.setRecipeInstruction(recipeInstruction2);
//
//        RecipeInstruction recipeInstruction = new RecipeInstruction("COOKING INSTRUCTIONS");
//
//        RecipeIngredient riBacon = new RecipeIngredient(bacon,200, Measurement.G);
//        RecipeIngredient riEgg = new RecipeIngredient(egg,2, Measurement.ST);
//        RecipeIngredient riFlower = new RecipeIngredient(flower,2,Measurement.DL);
//        RecipeIngredient riMilk = new RecipeIngredient(milk,4,Measurement.DL);
//        Recipe recipe = new Recipe("Fläskpankana",recipeInstruction);
//        recipe.addRecipeCategory(fat);
//        recipe.addRecipeIngredient(riBacon);
//        recipe.addRecipeIngredient(riEgg);
//        recipe.addRecipeIngredient(riFlower);
//        recipe.addRecipeIngredient(riMilk);
//        recipe.setRecipeInstruction(recipeInstruction);
//        recipeRepository.save(recipe);
//        recipeRepository.save(recipe2);
//

//        Ingredient test1 = ingredientRepository.findByIngredientName("Bacon").get();
//        List<Ingredient> test2 = ingredientRepository.findIngredientByNameContaining("o");
//        System.out.println("--------------"+test1);
//        System.out.println("--------------"+test2);

//        List<Recipe> test1 = recipeRepository.findRecipeByNameContaining("ä");
//        List<Recipe> test2 = recipeRepository.findRecipeByIngredient("milk");
//        List<Recipe> test3 = recipeRepository.findRecipeByCategory("Fatty food");
//        List<String> c = new ArrayList<>();
//        c.add("Fatty food");
//        List<Recipe> test4 = recipeRepository.findRecipeByCategories(c);
//        System.out.println("-------------"+test1);
//        System.out.println("-------------"+test2);
//        System.out.println("--------------"+test3);
//        System.out.println("--------------"+test4);

//        System.out.println("---------------------"+swedish.getRecipeList().size());
//        System.out.println("---------------------"+recipe2);
//        System.out.println("---------------------"+recipe);

        Recipe recipe6 = createRecipeService.createAndSave("Fläskpankaka","Sätt ugnen på 200°C. Häll hälften av mjölken i en skål." +
                "Vispa ner mjöl och salt till en jämn smet. Häll i resten av mjölken. Vispa sist ner äggen.\n" +
                "Skär fläsket i små tärningar. Bryn dem lätt i en stekpanna. Häll över i en smord långpanna. Häll smeten över fläsktärningarna.\n"+
                "Grädda i mitten av ugnen ca 25 min. Servera med lingonsylt.",
                Arrays.asList("Mjölk","Ägg","Mjöl","Salt","Bacon"),
                Arrays.asList(Measurement.DL,Measurement.ST,Measurement.DL,Measurement.KRM,Measurement.G),
                Arrays.asList(4.0,2.0,2.0,0.5,200.0),
                Arrays.asList("Husmanskost","Svensk","Lättlagad"));
        System.out.println(recipe6);
    }
}
