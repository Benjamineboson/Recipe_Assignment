package com.example.recipe_assignment.service;

import com.example.recipe_assignment.data.RecipeRepository;
import com.example.recipe_assignment.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase
public class CreateRecipeServiceImplTest {

    @Autowired
    private CreateRecipeService createRecipeService;
    @Autowired
    private RecipeRepository recipeRepository;




    @Test
    public void createEmptyRecipe_test(){
        Recipe actual = createRecipeService.createAndSaveEmptyRecipe("Test recipe1",new RecipeInstruction("Test instruction1"));
        Recipe expected = recipeRepository.findByRecipeName("Test recipe1").get();

        assertNotNull(actual);
        assertNotNull(expected);
        assertEquals(expected,actual);
    }


    @Test
    public void createRecipe_test(){
        Recipe actual = createRecipeService.createAndSave("Test recipe2","Test instruction2",
                Arrays.asList("Test ingredient"),Arrays.asList(Measurement.KRM),Arrays.asList(0.0),Arrays.asList("Test category"));
        Recipe expected = recipeRepository.findByRecipeName("Test recipe2").get();

        assertNotNull(actual);
        assertNotNull(expected);
        assertEquals(expected,actual);
    }
}
