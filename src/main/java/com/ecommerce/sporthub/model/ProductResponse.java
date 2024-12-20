package com.ecommerce.sporthub.model;

import com.ecommerce.sporthub.entity.Brand;
import com.ecommerce.sporthub.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String pictureUrl;
    private String productBrand;
    private String productType;
}
