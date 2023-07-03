package com.solread.meganspantry.repository;

import com.solread.meganspantry.model.RecipeIngredientAmount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredientAmount, Integer> {
    List<RecipeIngredientAmount> findAll();
}
