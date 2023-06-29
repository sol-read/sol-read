package com.solread.meganspantry.repository;

import com.solread.meganspantry.model.RecipeIngredientAmount;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredientAmount, Integer> {

}
