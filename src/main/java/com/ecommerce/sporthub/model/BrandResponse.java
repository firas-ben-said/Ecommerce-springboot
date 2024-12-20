package com.ecommerce.sporthub.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private Integer id;
    private String name;
}
