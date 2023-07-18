package com.solread.meganspantry.controller;

import com.solread.meganspantry.enums.IngredientUnit;
import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    public IngredientController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping(value = "/all")
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping(value = "/units")
    public List<IngredientUnit> getIngredientUnits() {
        return Arrays.asList(IngredientUnit.values());
    }

    @GetMapping(value = "/{id}")
    public Ingredient getIngredientById(@PathVariable("id") Integer id) {
        return ingredientRepository.findById(id).get();
    }

    @PostMapping(value = "/add",
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ingredient addNewIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @GetMapping(value = "/{id}/addMore/{amount}")
    public String addMoreOfAnIngredient(@PathVariable("id") Integer id, @PathVariable("amount") Integer amountToBeAdded) {
        Ingredient ingredient = ingredientRepository.findById(id).get();
        ingredient.addAmountToPantry(amountToBeAdded);

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        String returnString = "Successfully added " + amountToBeAdded + " ";
        if(savedIngredient.getUnit() == IngredientUnit.NONE) {
            returnString += savedIngredient.getName() + " to the pantry!";
        } else {
            returnString +=  savedIngredient.getUnit() + " of " + savedIngredient.getName() + " to the pantry!";
        }

        return returnString;
    }

    @GetMapping(value = "/inPantry")
    public List<Ingredient> getIngredientsInPantry() {
        return ingredientRepository.findByAmountInPantryGreaterThan(0);
    }

}
