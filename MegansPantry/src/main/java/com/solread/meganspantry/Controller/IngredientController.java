package com.solread.meganspantry.Controller;

import com.solread.meganspantry.Model.Ingredient;
import com.solread.meganspantry.Repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    IngredientRepository ingredientRepository;

    @GetMapping(value = "/{id}")
    public Ingredient getIngredientById(@PathVariable("id") long id) {
        Optional<Ingredient> maybeIngredient = ingredientRepository.findById(id);
        if(!maybeIngredient.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return maybeIngredient.get();
    }
}
