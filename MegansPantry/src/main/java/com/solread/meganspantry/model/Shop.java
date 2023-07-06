package com.solread.meganspantry.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shopping_trips")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "shop")
    @Column(name = "purchases")
    private List<Purchase> purchases;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "supermarket")
    private String supermarket;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public List<Purchase> getPurchases() { return purchases; }
    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }

    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }

    public String getSupermarket() { return supermarket; }
    public void setSupermarket(String supermarket) { this.supermarket = supermarket; }


    public Shop(List<Purchase> purchases, BigDecimal totalCost, String supermarket) {
        this.purchases = purchases;
        this.totalCost = totalCost;
        this.supermarket = supermarket;
    }

}
