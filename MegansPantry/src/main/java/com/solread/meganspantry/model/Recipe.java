package com.solread.meganspantry.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @Column(name = "vegetarian", nullable = false)
    private boolean isVegetarian = false;

    @Column(name = "vegan", nullable = false)
    private boolean isVegan = false;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public boolean isVegetarian() { return isVegetarian; }
    public boolean isVegan() { return isVegan; }

    public Recipe(String name, List<Ingredient> ingredients, boolean isVegetarian, boolean isVegan) {

        this.name = name;
        this.ingredients = ingredients;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

}
