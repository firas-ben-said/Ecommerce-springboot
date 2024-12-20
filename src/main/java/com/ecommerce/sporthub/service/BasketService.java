package com.ecommerce.sporthub.service;

import com.ecommerce.sporthub.entity.Basket;
import com.ecommerce.sporthub.model.BasketResponse;

import java.util.List;

public interface BasketService {
    List<BasketResponse> getAllBaskets();
    BasketResponse getBasketById(String basketId);
    void deleteBasketById(String basketId);
    BasketResponse createBasket(Basket basket);
}
