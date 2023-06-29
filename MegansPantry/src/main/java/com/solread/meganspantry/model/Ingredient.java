package com.solread.meganspantry.model;

import com.solread.meganspantry.enums.IngredientUnit;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.EnumType;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    public Ingredient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private IngredientUnit unit = IngredientUnit.NONE;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    @Column(name = "vegetarian")
    private boolean isVegetarian = false;

    @Column(name = "vegan")
    private boolean isVegan = false;

    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public IngredientUnit getUnit() { return unit; }
    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }

    public List<Recipe> getRecipes() { return recipes; }

    public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }

    public boolean isVegetarian() { return isVegetarian; }
    public void setVegetarian(boolean vegetarian) {
        this.isVegetarian = vegetarian;
    }

    public boolean isVegan() { return isVegan; }
    public void setVegan(boolean vegan) {
        this.isVegan = vegan;
    }

    public Ingredient(String name, IngredientUnit unit, List<Recipe> recipes, boolean vegetarian, boolean vegan) {
        this.name = name;
        this.unit = unit;
        this.recipes = recipes;
        this.isVegetarian = vegetarian;
        this.isVegan = vegan;
    }


}
