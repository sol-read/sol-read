package com.solread.meganspantry.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipe")
public class Recipe {

    public Recipe() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients;

    @Column(name = "vegetarian", nullable = false)
    private boolean isVegetarian = false;

    @Column(name = "vegan", nullable = false)
    private boolean isVegan = false;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.recipeIngredients = ingredients;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public List<RecipeIngredient> getIngredients() { return recipeIngredients; }
    public boolean isVegetarian() { return isVegetarian; }
    public boolean isVegan() { return isVegan; }

    public Recipe(String name, List<RecipeIngredient> ingredients) {

        this.name = name;
        this.recipeIngredients = ingredients;

        isVegetarian = true;
        isVegan = true;
        for(RecipeIngredient ingredient : ingredients) {
            if(!ingredient.getIngredient().isVegetarian()) {
                isVegetarian = false;
                isVegan = false;
                break;
            } else if(!ingredient.getIngredient().isVegan()) {
                isVegan = false;
                break;
            }
        }

    }
}
