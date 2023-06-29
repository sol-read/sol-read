package com.solread.meganspantry.model;

import com.solread.meganspantry.composite.IngredientAmount;

import javax.persistence.*;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredientAmount {

    public RecipeIngredientAmount() {
    }

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private IngredientAmount id;

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "amount")
    private Integer amount;


    public IngredientAmount getId() { return id; }

    public Recipe getRecipe() { return recipe; }

    public Ingredient getIngredient() { return ingredient; }

    public Integer getAmount() { return amount; }

    public void setId(IngredientAmount id) {
        this.id = id;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public RecipeIngredientAmount(IngredientAmount id, Recipe recipe, Ingredient ingredient, Integer amount) {

        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
    }


}
