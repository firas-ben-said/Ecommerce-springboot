package com.ecommerce.sporthub.repository;

import com.ecommerce.sporthub.entity.Basket;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository<Basket, String> {
}
