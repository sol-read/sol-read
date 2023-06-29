package com.solread.meganspantry.composite;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class IngredientAmount implements Serializable {

    @Column(name = "recipe_id")
    private Integer recipeId;

    @Column(name = "ingredient_id")
    private Integer ingredientId;

    public Integer getRecipeId() { return recipeId; }
    public Integer getIngredientId() { return ingredientId; }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public IngredientAmount() {
    }

    public IngredientAmount(Integer recipeId, Integer ingredientId) {

        this.recipeId = recipeId;
        this.ingredientId = ingredientId;

    }

}
