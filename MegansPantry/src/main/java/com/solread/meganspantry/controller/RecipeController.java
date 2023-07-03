package com.solread.meganspantry.controller;

import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.model.Recipe;
import com.solread.meganspantry.model.RecipeIngredientAmount;
import com.solread.meganspantry.repository.RecipeRepository;
import com.solread.meganspantry.repository.RecipeIngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeController(final RecipeRepository recipeRepository, final RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }


    @GetMapping(value = "/all")
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    @GetMapping(value = "/vegetarian")
    public List<Recipe> getVegetarianRecipes() {
        return recipeRepository.findByIsVegetarianTrue();
    }
    @GetMapping(value = "/vegan")
    public List<Recipe> getVeganRecipes() {
        return recipeRepository.findByIsVeganTrue();
    }

    @GetMapping(value = "/{id}")
    public Recipe getRecipeById(@PathVariable("id") Integer id) {
        Optional<Recipe> maybeRecipe = recipeRepository.findById(id);
        if(!maybeRecipe.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Recipe recipe = maybeRecipe.get();
        List<String> ingredientNames = new ArrayList<>();
        for(Ingredient ingredient : recipe.getIngredients()) {
            ingredientNames.add(ingredient.getName());
        }
        recipe.setIngredientNames(ingredientNames);
        return recipe;
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

    @GetMapping(value = "/canMake")
    public List<Recipe> getRecipesThatCanBeMadeWithAvailableIngredients() {

        List<Recipe> recipesThatCanBeMade = recipeRepository.findAll();

        Iterator<Recipe> recipeIterator = recipesThatCanBeMade.iterator();
        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            for(Ingredient ingredient : recipe.getIngredients()) {
                Integer amountNeeded = recipeIngredientRepository
                        .findByRecipeIdAndIngredientId(recipe.getId(),ingredient.getId())
                        .getAmount();
                if(ingredient.getAmountInPantry() < amountNeeded) {
                    recipeIterator.remove();
                    break;
                }
            }
        }

        return recipesThatCanBeMade;
    }

}
