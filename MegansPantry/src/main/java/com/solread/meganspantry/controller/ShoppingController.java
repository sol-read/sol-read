package com.solread.meganspantry.controller;

import com.solread.meganspantry.model.Purchase;
import com.solread.meganspantry.repository.ShoppingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping")
public class ShoppingController {

    private ShoppingRepository shoppingRepository;

    public ShoppingController(final ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }


    @GetMapping(value = "/goShopping")
    public String goShopping() {
        return "Nothing to buy!";
    }

}
