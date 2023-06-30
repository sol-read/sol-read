package com.solread.meganspantry.controller;

import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.model.Recipe;
import com.solread.meganspantry.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping(value = "vegetarian")
    public List<Recipe> getVegetarianRecipes() {
        return recipeRepository.findByIsVegetarianTrue();
    }
    @GetMapping(value = "vegan")
    public List<Recipe> getVeganRecipes() {
        return recipeRepository.findByIsVeganTrue();
    }

    @GetMapping(value = "/{id}")
    public Recipe getRecipeById(@PathVariable("id") Integer id) {
        Optional<Recipe> maybeRecipe = recipeRepository.findById(id);
        if(!maybeRecipe.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return maybeRecipe.get();
    }

    @GetMapping(value = "/{id}/ingredients")
    public List<String> getRecipeIngredients(@PathVariable("id") Integer id) {
        Optional<Recipe> maybeRecipe = recipeRepository.findById(id);
        if(!maybeRecipe.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<Ingredient> recipeIngredients = maybeRecipe.get().getIngredients();
        List<String> ingredientList = new ArrayList<>();
        for(Ingredient ingredient : recipeIngredients) {
            ingredientList.add(ingredient.getName());
        }
        return ingredientList;
    }

    @PutMapping(value = "/add")
    public Recipe addRecipe(@RequestBody Recipe recipe) {

        Recipe addedRecipe = recipeRepository.save(recipe);
        return recipe;
    }

}
