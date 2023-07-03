package com.solread.meganspantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.solread.meganspantry.model.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    public List<Recipe> findAll();
    public List<Recipe> findByIsVeganTrue();
    public List<Recipe> findByIsVegetarianTrue();
    public Recipe findByName(String name);
}
