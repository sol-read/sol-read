package com.solread.meganspantry.controller;

import com.solread.meganspantry.enums.IngredientUnit;
import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    public IngredientController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "This is working";
    }

    @GetMapping(value = "/all")
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Ingredient getIngredientById(@PathVariable("id") Integer id) {
        Optional<Ingredient> maybeIngredient = ingredientRepository.findById(id);
        if(!maybeIngredient.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return maybeIngredient.get();
    }

    @PutMapping(value = "/addNew")
    public Ingredient addNewIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientRepository.save(ingredient);
        return newIngredient;
    }

    @GetMapping(value = "/{id}/addMore/{amount}")
    public String addMoreOfAnIngredient(@PathVariable("id") Integer id, @PathVariable("amount") Integer amountToBeAdded) {
        Optional<Ingredient> maybeIngredient = ingredientRepository.findById(id);
        if(!maybeIngredient.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Ingredient ingredient = maybeIngredient.get();
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
