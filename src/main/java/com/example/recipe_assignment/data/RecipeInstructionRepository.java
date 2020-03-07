package com.example.recipe_assignment.data;

import com.example.recipe_assignment.entity.RecipeInstruction;
import org.springframework.data.repository.CrudRepository;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction,String> {
}
