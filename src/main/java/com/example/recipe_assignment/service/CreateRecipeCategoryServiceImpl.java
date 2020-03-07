package com.example.recipe_assignment.service;

import com.example.recipe_assignment.entity.RecipeCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRecipeCategoryServiceImpl implements CreateRecipeCategoryService {

    @Override
    @Transactional
    public RecipeCategory create(String recipeCategoryName){
     return new RecipeCategory(recipeCategoryName);
    }

}
