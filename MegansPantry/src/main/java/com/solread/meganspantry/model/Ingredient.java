package com.solread.meganspantry.model;

import com.solread.meganspantry.enums.IngredientUnit;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.EnumType;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    public Ingredient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private IngredientUnit unit = IngredientUnit.NONE;

    @Column(name = "vegetarian")
    private boolean vegetarian = false;

    @Column(name = "vegan")
    private boolean vegan = false;

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

    public boolean isVegetarian() { return vegetarian; }
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() { return vegan; }
    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public Ingredient(String name, IngredientUnit unit, boolean vegetarian, boolean vegan) {
        this.name = name;
        this.unit = unit;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
    }


}
