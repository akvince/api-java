package com.tuto.api.controllers;

import com.tuto.api.entities.ProductEntity;
import com.tuto.api.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Api(tags = "Product Controller", description = "Operations pertaining to products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ApiOperation(value = "Get all products", response = List.class)
    public List<ProductEntity> getAllProducts() {
        // Implémentation pour récupérer tous les produits
        return productService.getAllProducts();
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get a product by Uuid", response = ProductEntity.class)
    public Optional<ProductEntity> getProductById(@PathVariable UUID uuid) {
        // Implémentation pour récupérer un produit par son ID
        return productService.getByUuid(uuid);
    }

    @PostMapping
    @ApiOperation(value = "Create a new product", response = ProductEntity.class)
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        // Implémentation pour créer un nouveau produit
        return productService.saveProduct(product);
    }

    @PutMapping("/{productId}")
    @ApiOperation(value = "Update a product by ID", response = ProductEntity.class)
    public ProductEntity updateProduct(@PathVariable Long productId, @RequestBody ProductEntity updatedProduct) {
        // Implémentation pour mettre à jour un produit par son ID
        return null;
    }

    @PatchMapping("/{uuid}")
    @ApiOperation(value = "Update a product by uuid", response = ProductEntity.class)
    public ResponseEntity<ProductEntity> patchProduct(
            @PathVariable UUID uuid,
            @RequestBody ProductEntity partialProduct
    ) {
        Optional<ProductEntity> updatedProductOptional = productService.patchProduct(uuid, partialProduct);

        return updatedProductOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "Delete a product by UUID")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID uuid) {
        // Implémentation pour supprimer un produit par son UUID
        boolean deletionResult = productService.deleteProduct(uuid);

        if (deletionResult) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
