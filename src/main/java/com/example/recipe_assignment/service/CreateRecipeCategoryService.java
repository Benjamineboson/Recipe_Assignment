package com.example.recipe_assignment.service;

import com.example.recipe_assignment.entity.RecipeCategory;
import org.springframework.transaction.annotation.Transactional;

public interface CreateRecipeCategoryService {
    @Transactional
    RecipeCategory create(String recipeCategoryName);
}
