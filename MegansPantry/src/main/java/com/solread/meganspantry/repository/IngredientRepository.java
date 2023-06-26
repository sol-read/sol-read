package com.solread.meganspantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.solread.meganspantry.model.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    public List<Ingredient> findByVeganTrue();
    public List<Ingredient> findByVegetarianTrue();

}
