package com.solread.meganspantry.controller;

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
    public Iterable<Ingredient> getAllIngredients() {
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

    @PutMapping(value = "/add")
    public Ingredient addNewIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientRepository.save(ingredient);
        return newIngredient;
    }

}
