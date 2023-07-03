package com.solread.meganspantry.model;

import com.solread.meganspantry.Exception.InvalidActionSoFarException;
import com.solread.meganspantry.repository.IngredientRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "shop")
public class Purchase {

    private IngredientRepository ingredientRepository;
    public Purchase() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate dateOfPurchase;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "amount_bought")
    private Integer amountBought;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Integer shopId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDateOfPurchase() { return dateOfPurchase; }
    public void setDateOfPurchase() { this.dateOfPurchase = LocalDate.now(); }
    public void setDateOfPurchase(LocalDate dateOfPurchase) { this.dateOfPurchase = dateOfPurchase; }

    public Ingredient getIngredient() { return ingredient; }

    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public Integer getAmountBought() { return amountBought; }
    public void setAmountBought(Integer amountBought) { this.amountBought = amountBought; }

    public Integer getShopId() { return shopId; }
    public void setShopId(Integer shopId) { this.shopId = shopId; }

    public Purchase(Ingredient ingredient, BigDecimal cost, Integer amountBought) {

        this.dateOfPurchase = LocalDate.now();
        this.ingredient = ingredient;
        this.cost = cost;
        this.amountBought = amountBought;
    }

    public void addPurchaseToPantry(Purchase purchase) throws InvalidActionSoFarException {
        Optional<Ingredient> boughtIngredient = ingredientRepository.findById(purchase.ingredient.getId());
        if(!boughtIngredient.isPresent()) {
            throw new InvalidActionSoFarException();
        } else {
            Ingredient purchasedIngredient = boughtIngredient.get();
            purchasedIngredient.addAmountToPantry(purchase.amountBought);
        }
    }
}
