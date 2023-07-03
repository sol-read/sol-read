package com.solread.meganspantry.repository;

import org.springframework.data.repository.CrudRepository;

import com.solread.meganspantry.model.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    List<Ingredient> findAll();
    public List<Ingredient> findByIsVeganTrue();
    public List<Ingredient> findByIsVegetarianTrue();
    public List<Ingredient> findByAmountInPantryGreaterThan(Integer amountInPantry);

}
