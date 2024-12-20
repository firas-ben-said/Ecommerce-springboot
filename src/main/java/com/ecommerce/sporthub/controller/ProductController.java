package com.ecommerce.sporthub.controller;

import com.ecommerce.sporthub.model.BrandResponse;
import com.ecommerce.sporthub.model.ProductResponse;
import com.ecommerce.sporthub.model.TypeResponse;
import com.ecommerce.sporthub.service.BrandService;
import com.ecommerce.sporthub.service.ProductService;
import com.ecommerce.sporthub.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final TypeService typeService;

    public ProductController(ProductService productService, BrandService brandService, TypeService typeService) {
        this.productService = productService;
        this.brandService = brandService;
        this.typeService = typeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id")Integer id) {
        ProductResponse productResponse = productService.getProductById(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    /*@PreAuthorize("isAuthenticated()")*/
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "typeId", required = false) Integer typeId,
            @RequestParam(name = "sort", defaultValue = "name") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order
            ) {
        // Convert the sort and order parameters to a Sort object
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sorting = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<ProductResponse> productResponses = productService.getAllProducts(pageable, brandId, typeId, keyword);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandResponse> brandResponses = brandService.getAllBrands();
        return new ResponseEntity<>(brandResponses, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<TypeResponse>> getTypes() {
        List<TypeResponse> typeResponses = typeService.getAllTypes();
        return new ResponseEntity<>(typeResponses, HttpStatus.OK);
    }
}
