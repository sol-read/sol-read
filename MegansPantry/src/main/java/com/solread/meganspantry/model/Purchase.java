package com.solread.meganspantry.model;

import com.solread.meganspantry.repository.IngredientRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase")
public class Purchase {

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
    private Shop shop;

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

    public Shop getShop() { return shop; }
    public void setShop(Shop shop) { this.shop = shop; }

    public Purchase(Ingredient ingredient, BigDecimal cost, Integer amountBought) {

        this.dateOfPurchase = LocalDate.now();
        this.ingredient = ingredient;
        this.cost = cost;
        this.amountBought = amountBought;
    }

}
