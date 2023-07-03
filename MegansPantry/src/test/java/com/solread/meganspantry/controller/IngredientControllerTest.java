package com.solread.meganspantry.controller;

import com.solread.meganspantry.enums.IngredientUnit;
import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.repository.IngredientRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientControllerTest {

    @Mock
    private IngredientRepository ingredientRepository;
    private IngredientController ingredientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(ingredientRepository);

    }


    @Test
    void getAllIngredients() {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1,"garlic",IngredientUnit.CLOVES,true,true,12));
        ingredients.add(new Ingredient(2,"meatballs",IngredientUnit.TINS,false,false,3));
        ingredients.add(new Ingredient(3,"cheese",IngredientUnit.GRAMS,true,false,550));
        when(ingredientRepository.findAll()).thenReturn(ingredients);

        List<Ingredient> result = ingredientController.getAllIngredients();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void getIngredientById() {
        Ingredient ingredient = new Ingredient(1,"cheese",IngredientUnit.GRAMS,true,false,200);
        when(ingredientRepository.findById(1)).thenReturn(Optional.of(ingredient));
        when(ingredientRepository.findById(2)).thenReturn(Optional.empty());

        Ingredient result = ingredientController.getIngredientById(1);

        assertEquals(result, ingredient);
        assertThrows(ResponseStatusException.class, () -> {
            ingredientController.getIngredientById(2);
        });
    }

    @Test
    void addMoreOfAnIngredient() {

        Ingredient ingredient = new Ingredient(1,"cheese",IngredientUnit.GRAMS,true,false,100);
        when(ingredientRepository.findById(1)).thenReturn(Optional.of(ingredient));
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        String resultString = ingredientController.addMoreOfAnIngredient(1,100);

        assertEquals(ingredient.getAmountInPantry(),200);
        assertEquals(resultString,"Successfully added 100 GRAMS of cheese to the pantry!");
    }

}