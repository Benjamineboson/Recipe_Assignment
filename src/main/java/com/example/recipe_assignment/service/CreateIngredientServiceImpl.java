package com.example.recipe_assignment.service;

import com.example.recipe_assignment.data.IngredientRepository;
import com.example.recipe_assignment.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateIngredientServiceImpl implements CreateIngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public CreateIngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public Ingredient createAndSave(String ingredientName) {
        if (ingredientName == null) throw new RuntimeException("Ingredient name is null");
        if (ingredientRepository.findByIngredientName(ingredientName).isPresent()) throw new RuntimeException("Ingredient already exists");
        return ingredientRepository.save(new Ingredient(ingredientName));
    }


}
