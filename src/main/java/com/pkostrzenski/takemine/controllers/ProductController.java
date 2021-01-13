package com.pkostrzenski.takemine.controllers;

import com.pkostrzenski.takemine.custom_exceptions.ServiceException;
import com.pkostrzenski.takemine.models.Product;
import com.pkostrzenski.takemine.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("api/products/{productId}")
    public ResponseEntity<?> getProduct(@NotBlank @PathVariable("productId") Integer productId){
        try {
            return ResponseEntity.ok(productService.getProductById(productId));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("api/products/from-city/{cityId}")
    public ResponseEntity<?> getProductsFromCity(@NotBlank @PathVariable("cityId") Integer cityId){
        return ResponseEntity.ok(productService.getAllProductsByCityId(cityId));
    }

    @GetMapping("api/categories")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(productService.getAllCategories());
    }

    @GetMapping("api/categories/{categoryId}/item-types")
    public ResponseEntity<?> getItemTypesFromCategory(@NotBlank @PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(productService.getItemTypesByCategoryId(categoryId));
    }

    @PostMapping("api/products")
    public ResponseEntity<?> postProduct(Authentication authentication, @Valid @RequestBody Product product) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(productService.addProduct(product, username));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("api/products/{productId}/buy")
    public ResponseEntity<?> buyProduct(Authentication authentication, @NotBlank @PathVariable("productId") Integer productId) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(productService.buyProduct(productId, username));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
