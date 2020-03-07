package com.example.recipe_assignment.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeCategoryId;

    private String recipeCategoryName;
    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "recipe_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipeList = new ArrayList<>();


    public RecipeCategory(int recipeCategoryId, String recipeCategoryName) {
        this.recipeCategoryId = recipeCategoryId;
        this.recipeCategoryName = recipeCategoryName;
    }

    public RecipeCategory(String recipeCategoryName) {
        this(0,recipeCategoryName);
    }

    public RecipeCategory(){}

    public int getRecipeCategoryId() {
        return recipeCategoryId;
    }

    public String getRecipeCategoryName() {
        return recipeCategoryName;
    }

    public void setRecipeCategoryName(String recipeCategoryName) {
        this.recipeCategoryName = recipeCategoryName;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategory that = (RecipeCategory) o;
        return recipeCategoryId == that.recipeCategoryId &&
                Objects.equals(recipeCategoryName, that.recipeCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeCategoryId, recipeCategoryName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecipeCategory{");
        sb.append("recipeCategoryId=").append(recipeCategoryId);
        sb.append(", recipeCategoryName='").append(recipeCategoryName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
