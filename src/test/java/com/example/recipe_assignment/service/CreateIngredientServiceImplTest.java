package com.example.recipe_assignment.service;
import com.example.recipe_assignment.data.IngredientRepository;
import com.example.recipe_assignment.entity.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class CreateIngredientServiceImplTest {

    @Autowired
    private CreateIngredientService createIngredientService;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void createIngredient_test(){
        Ingredient actual = createIngredientService.createAndSave("Test ingredient");
        Ingredient expected = ingredientRepository.findByIngredientName("Test ingredient").get();

        assertNotNull(actual);
        assertNotNull(expected);
        assertEquals(expected,actual);
    }
}
