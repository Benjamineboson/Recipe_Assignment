package com.example.recipe_assignment.service;

import com.example.recipe_assignment.entity.RecipeCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class CreateRecipeCategoryServiceImplTest {

    @Autowired
    private CreateRecipeCategoryService createRecipeCategoryService;

    @Test
    public void createRecipeCategory_test(){
        RecipeCategory actual = createRecipeCategoryService.create("Test category");
        String expected = "Test category";

        assertNotNull(actual);
        assertEquals(expected,actual.getRecipeCategoryName());
    }


}
