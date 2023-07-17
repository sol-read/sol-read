package com.solread.meganspantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.solread.meganspantry.model.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    public List<Recipe> findAll();
    public List<Recipe> findByIsVegan(Boolean vegan);
    public List<Recipe> findByIsVegetarian(Boolean vegetarian);
    public List<Recipe> findByIsVegetarianAndIsVegan(Boolean vegetarian, Boolean vegan);
    public Recipe findByName(String name);
}
