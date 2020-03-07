package com.example.recipe_assignment.data;
import com.example.recipe_assignment.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private TestEntityManager em;

    private List<Recipe> data(){
        return Arrays.asList(
                new Recipe("Tomato soup"),
                new Recipe("Egg and Bacon"),
                new Recipe("Pizza")
        );
    }

    private String recipeInstructionId;
    private Recipe testRecipe;
    private int testRecipeId;

    @BeforeEach
    void setUp() {
        Ingredient milk = new Ingredient("Milk");

        RecipeCategory recipeCategory = new RecipeCategory("Test Category");
        RecipeCategory recipeCategory2 = new RecipeCategory("Test Category2");

        RecipeIngredient recipeIngredient = new RecipeIngredient(milk,2, Measurement.DL);
        RecipeIngredient recipeIngredient2 = new RecipeIngredient(milk,2, Measurement.DL);

        RecipeInstruction recipeInstruction = new RecipeInstruction("Test Instruction");
        recipeInstructionId = recipeInstruction.getRecipeInstructionId();
        List<Recipe> persisted = data().stream()
                .collect(Collectors.toList());
        persisted.forEach(recipeRepository::save);
        persisted.forEach(r -> r.addRecipeCategory(recipeCategory));
        testRecipe = recipeRepository.save(new Recipe("Test Recipe",new RecipeInstruction("Test Instruction 2")));
        testRecipeId = testRecipe.getRecipeId();
        recipeRepository.findByRecipeName("Pizza").get().addRecipeIngredient(recipeIngredient2);
        testRecipe.addRecipeCategory(recipeCategory2);
        testRecipe.addRecipeIngredient(recipeIngredient);
        em.flush();
    }




    @Test
    public void findByRecipeName_test(){
         int actual = recipeRepository.findByRecipeName("Test Recipe").get().getRecipeId();

         assertNotNull(actual);
         assertEquals(testRecipeId,actual);
    }

    @Test
    public void findRecipeByNameContaining_test(){
        List<Recipe> actual = recipeRepository.findRecipeByNameContaining("o");

        assertNotNull(actual);
        assertEquals(2,actual.size());
    }

    @Test
    public void findRecipeByIngredient_test(){
        List<Recipe> actual = recipeRepository.findRecipeByIngredient("Milk");

        assertNotNull(actual);
        assertEquals(2,actual.size());
    }

    @Test
    public void findRecipeByCategory_test(){
        List<Recipe> actual = recipeRepository.findRecipeByCategory("Test Category");

        assertNotNull(actual);
        assertEquals(3,actual.size());
    }

    @Test
    public void findRecipeByCategories_test(){
    List<Recipe> actual = recipeRepository.findRecipeByCategories(Arrays.asList(
            "Test Category","Test Category2"
    ));

    assertNotNull(actual);
    assertEquals(4,actual.size());
    }

}
