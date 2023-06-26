package com.solread.meganspantry.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "amount")
    private Integer amount;


    public long getId() { return id; }

    public Recipe getRecipe() { return recipe; }

    public Ingredient getIngredient() { return ingredient; }

    public Integer getAmount() { return amount; }

    public void setId(Integer id) {
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

    public RecipeIngredient(Integer id, Recipe recipe, Ingredient ingredient, Integer amount) {

        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
    }


}
