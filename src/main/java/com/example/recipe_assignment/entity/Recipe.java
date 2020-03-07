package com.example.recipe_assignment.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private String recipeName;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
            orphanRemoval = true,
            mappedBy = "recipe"
    )
    private List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST}
    )
    @JoinColumn(name = "instruction_id")
    private RecipeInstruction recipeInstruction;
    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "recipe_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_category_id")
    )
    private List<RecipeCategory> recipeCategoryList = new ArrayList<>();

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredientList, RecipeInstruction recipeInstruction, List<RecipeCategory> recipeCategoryList) {
        this.recipeName = recipeName;
        this.recipeIngredientList = recipeIngredientList;
        this.recipeInstruction = recipeInstruction;
        this.recipeCategoryList = recipeCategoryList;
    }

    public Recipe(int recipeId, String recipeName, RecipeInstruction recipeInstruction) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeInstruction = recipeInstruction;
    }

    public Recipe(String recipeName, RecipeInstruction recipeInstruction) {
        this(0,recipeName,recipeInstruction);
    }

    public Recipe(String recipeName) {
        this(0,recipeName,null);
    }

    public Recipe(){}

    public boolean addRecipeCategory(RecipeCategory recipeCategory){
        if (recipeCategoryList.contains(recipeCategory)) return false;
        if (recipeCategory == null) return false;
        return recipeCategoryList.add(recipeCategory);
    }

    public boolean removeRecipeCategory(RecipeCategory recipeCategory){
        if (!recipeCategoryList.contains(recipeCategory)) return false;
        if (recipeCategory == null) return false;
        return recipeCategoryList.remove(recipeCategory);
    }

    public boolean addRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredient == null) return false;
        if (recipeIngredient.getRecipe() != null) return false;
        if (recipeIngredientList.contains(recipeIngredient)) return false;
        recipeIngredient.setRecipe(this);
        return recipeIngredientList.add(recipeIngredient);
    }

    public boolean removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredient == null) return false;
        if (recipeIngredient.getRecipe() != this) return false;
        recipeIngredient.setRecipe(null);
        return recipeIngredientList.remove(recipeIngredient);
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredientList() {
        return recipeIngredientList;
    }

    public void setRecipeIngredientList(List<RecipeIngredient> recipeIngredientList) {
        this.recipeIngredientList = recipeIngredientList;
    }

    public RecipeInstruction getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }

    public List<RecipeCategory> getRecipeCategoryList() {
        return recipeCategoryList;
    }

    public void setRecipeCategoryList(List<RecipeCategory> recipeCategoryList) {
        this.recipeCategoryList = recipeCategoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId &&
                Objects.equals(recipeName, recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeInstruction);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recipe{");
        sb.append("recipeId=").append(recipeId);
        sb.append(", recipeName='").append(recipeName).append('\'');
        sb.append(", recipeIngredientList=").append(recipeIngredientList);
        sb.append(", recipeInstruction=").append(recipeInstruction);
        sb.append(", recipeCategoryList=").append(recipeCategoryList);
        sb.append('}');
        return sb.toString();
    }
}
