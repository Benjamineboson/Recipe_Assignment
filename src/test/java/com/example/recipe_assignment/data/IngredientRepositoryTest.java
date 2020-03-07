package com.example.recipe_assignment.data;
import com.example.recipe_assignment.entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    private List<Ingredient> data(){
        return Arrays.asList(
                new Ingredient("Bacon"),
                new Ingredient("Tomato"),
                new Ingredient("Milk")
        );
    }

    @BeforeEach
    void setUp() {
        data().forEach(ingredientRepository::save);
    }

    @Test
    public void findByIngredientName_test(){
        String expected = "Bacon";
        String actual = ingredientRepository.findByIngredientName("Bacon").get().getIngredientName();

        assertNotNull(actual);
        assertEquals(expected,actual);
    }

    @Test
    public void findIngredientByNameContaining_test(){
        List<Ingredient> actual = ingredientRepository.findIngredientByNameContaining("o");

        assertNotNull(actual);
        assertEquals(2,actual.size());
    }
}
