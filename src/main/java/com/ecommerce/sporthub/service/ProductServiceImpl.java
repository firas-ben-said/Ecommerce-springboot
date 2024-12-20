package com.ecommerce.sporthub.service;

import com.ecommerce.sporthub.entity.Product;
import com.ecommerce.sporthub.exceptions.ProductNotFoundException;
import com.ecommerce.sporthub.model.ProductResponse;
import com.ecommerce.sporthub.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        log.info("Fetching product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found!"));
        // Convert the product to a product response and return it
        ProductResponse productResponse = convertToProductResponse(product);
        log.info("Fetched product by Product Id: {}", id);

        return productResponse;
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable, Integer brandId, Integer typeId, String keyword) {
        log.info("Fetching all products...");
        Specification<Product> spec = Specification.where(null);
        if (brandId != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("brand").get("id"), brandId));
        }
        if (typeId != null){
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("type").get("id"), typeId));
        }
        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), "%" + keyword + "%"));
        }
        log.info("Fetched all products!");
        // Fetch products from the database
        return productRepository.findAll(spec, pageable)
                .map(this::convertToProductResponse);
    }

    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .pictureUrl(product.getPictureUrl())
                .productBrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .build();
    }
}
