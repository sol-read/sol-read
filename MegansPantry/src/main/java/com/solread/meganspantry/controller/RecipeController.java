package com.solread.meganspantry.controller;

import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.model.Recipe;
import com.solread.meganspantry.model.RecipeIngredientAmount;
import com.solread.meganspantry.repository.RecipeRepository;
import com.solread.meganspantry.repository.RecipeIngredientRepository;
import com.solread.meganspantry.repository.IngredientRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(final RecipeRepository recipeRepository,
                            final RecipeIngredientRepository recipeIngredientRepository,
                            final IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @GetMapping(value = "/all")
    public List<Recipe> getAllRecipes(@RequestParam(required = false) Boolean vegetarian,
                                      @RequestParam(required = false) Boolean vegan,
                                      @RequestParam(required = false) Boolean canMake) {
        if(vegan == null && vegetarian == null && canMake == null) {
            return recipeRepository.findAll();
        } else if(vegan == null && canMake == null) {
            return recipeRepository.findByIsVegetarian(vegetarian);
        } else if(vegetarian == null && canMake == null) {
            return recipeRepository.findByIsVegan(vegan);
        } else if (vegan == null && vegetarian == null) {
            return getRecipesThatCanBeMadeWithAvailableIngredients();
        } else if (vegan == null) {
            List<Recipe> availableRecipes = getRecipesThatCanBeMadeWithAvailableIngredients();
            for(Recipe recipe : availableRecipes) {
                if(!recipe.isVegetarian()) {
                    availableRecipes.remove(recipe);
                    return availableRecipes;
                }
            }
        } else if (vegetarian == null) {
            List<Recipe> availableRecipes = getRecipesThatCanBeMadeWithAvailableIngredients();
            for(Recipe recipe : availableRecipes) {
                if(!recipe.isVegan()) {
                    availableRecipes.remove(recipe);
                    return availableRecipes;
                }
            }
        } else if (canMake == null) {
            return recipeRepository.findByIsVegetarianAndIsVegan(vegetarian,vegan);
        } else {
            List<Recipe> availableRecipes = getRecipesThatCanBeMadeWithAvailableIngredients();
            for(Recipe recipe : availableRecipes) {
                if(!recipe.isVegan() || !recipe.isVegetarian()) {
                    availableRecipes.remove(recipe);
                }
            }
            return availableRecipes;
        }
        return null;
    }

    @GetMapping(value = "/vegetarian")
    public List<Recipe> getVegetarianRecipes() {
        return recipeRepository.findByIsVegetarian(true);
    }
    @GetMapping(value = "/vegan")
    public List<Recipe> getVeganRecipes() {
        return recipeRepository.findByIsVegan(true);
    }

    @GetMapping(value = "/{id}")
    public Recipe getRecipeById(@PathVariable("id") Integer id) {
        return recipeRepository.findById(id).get();
    }

    @GetMapping(value = "/{id}/ingredients")
    public List<Ingredient> getRecipeIngredients(@PathVariable("id") Integer id) {
        Recipe recipe = recipeRepository.findById(id).get();
        List<RecipeIngredientAmount> recipeIngredientAmounts = recipeIngredientRepository.findByRecipeId(id);

        List<Ingredient> ingredientsInRecipe = recipe.getIngredients();
        for(int i=0;i<ingredientsInRecipe.size();i++) {
            Ingredient ingredient = ingredientsInRecipe.get(i);
            ingredient.setAmountNeeded(recipeIngredientAmounts.get(i).getAmount());
        }

        return ingredientsInRecipe;
    }

    @PostMapping(value = "/add",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public Recipe addRecipe(@RequestBody Map<String, Object> requestBody) {

        String recipeName = (String) requestBody.get("recipeName");
        List<Integer> ingredientIds = (List<Integer>) requestBody.get("ingredientIds");
        List<Integer> ingredientAmounts = (List<Integer>) requestBody.get("ingredientAmounts");

        List<Ingredient> ingredientList = new ArrayList<>();
        for(Integer ingredientId : ingredientIds) {
            ingredientList.add(ingredientRepository.findById(ingredientId).get());
        }
        Recipe newRecipe = new Recipe(recipeName, ingredientList);
        Recipe addedRecipe = recipeRepository.save(newRecipe);

        for(int i=0;i<ingredientAmounts.size();i++) {
            RecipeIngredientAmount recipeIngredientAmount = recipeIngredientRepository.findByRecipeIdAndIngredientId(addedRecipe.getId(),ingredientIds.get(i));
            recipeIngredientAmount.setAmount(ingredientAmounts.get(i));
            recipeIngredientRepository.save(recipeIngredientAmount);
        }
        return addedRecipe;
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
