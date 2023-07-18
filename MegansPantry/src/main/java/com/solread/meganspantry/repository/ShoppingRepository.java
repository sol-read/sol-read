package com.solread.meganspantry.repository;

import com.solread.meganspantry.enums.Supermarket;
import com.solread.meganspantry.model.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingRepository extends CrudRepository<Shop, Integer> {

    public List<Shop> findBySupermarket(Supermarket supermarket);

}
