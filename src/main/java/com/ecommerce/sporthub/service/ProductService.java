package com.ecommerce.sporthub.service;

import com.ecommerce.sporthub.model.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse getProductById(Integer id);
    Page<ProductResponse> getAllProducts(Pageable pageable, Integer brandId, Integer typeId, String keyword);
}
