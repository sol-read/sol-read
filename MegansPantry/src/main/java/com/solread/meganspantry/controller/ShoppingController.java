package com.solread.meganspantry.controller;

import com.solread.meganspantry.Exception.InvalidActionSoFarException;
import com.solread.meganspantry.model.Purchase;
import com.solread.meganspantry.model.Ingredient;
import com.solread.meganspantry.repository.IngredientRepository;
import com.solread.meganspantry.repository.PurchaseRepository;
import com.solread.meganspantry.repository.ShoppingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/shopping")
public class ShoppingController {

    private ShoppingRepository shoppingRepository;
    private IngredientRepository ingredientRepository;
    private PurchaseRepository purchaseRepository;

    public ShoppingController(final ShoppingRepository shoppingRepository,
                              final IngredientRepository ingredientRepository,
                              final PurchaseRepository purchaseRepository) {
        this.shoppingRepository = shoppingRepository;
        this.ingredientRepository = ingredientRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void addPurchaseToPantry(Purchase purchase) throws InvalidActionSoFarException {
        Optional<Ingredient> boughtIngredient = ingredientRepository.findById(purchase.getIngredient().getId());
        if(!boughtIngredient.isPresent()) {
            throw new InvalidActionSoFarException();
        } else {
            Ingredient purchasedIngredient = boughtIngredient.get();
            purchasedIngredient.addAmountToPantry(purchase.getAmountBought());
        }
    }

    public String unpackTheShopping(List<Purchase> purchases) throws InvalidActionSoFarException {
        for(Purchase purchase : purchases) {
            Optional<Ingredient> boughtIngredient = ingredientRepository.findById(purchase.getIngredient().getId());
            if(!boughtIngredient.isPresent()) {
                throw new InvalidActionSoFarException();
            } else {
                Ingredient purchasedIngredient = boughtIngredient.get();
                purchasedIngredient.addAmountToPantry(purchase.getAmountBought());
            }
        }
        return "Successfully went shopping!";
    }


    @GetMapping(value = "/goShopping")
    public String goShopping() {
        return "Nothing to buy!";
    }

}
