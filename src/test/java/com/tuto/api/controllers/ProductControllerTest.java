package com.tuto.api.controllers;

import com.tuto.api.entities.ProductEntity;
import com.tuto.api.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(new ProductEntity()));

        assertEquals(1, productController.getAllProducts().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProductById() {
        UUID uuid = UUID.randomUUID();
        when(productService.getByUuid(uuid)).thenReturn(Optional.of(new ProductEntity()));

        assertNotNull(productController.getProductById(uuid));
        verify(productService, times(1)).getByUuid(uuid);
    }

    @Test
    void createProduct() {
        ProductEntity product = new ProductEntity();
        when(productService.saveProduct(product)).thenReturn(product);

        assertEquals(product, productController.createProduct(product));
        verify(productService, times(1)).saveProduct(product);
    }

    @Test
    void patchProduct() {
        UUID uuid = UUID.randomUUID();
        ProductEntity partialProduct = new ProductEntity();
        when(productService.patchProduct(uuid, partialProduct)).thenReturn(Optional.of(new ProductEntity()));

        ResponseEntity<ProductEntity> responseEntity = productController.patchProduct(uuid, partialProduct);

        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(productService, times(1)).patchProduct(uuid, partialProduct);
    }

    @Test
    void deleteProduct() {
        UUID uuid = UUID.randomUUID();
        when(productService.deleteProduct(uuid)).thenReturn(true);

        ResponseEntity<String> responseEntity = productController.deleteProduct(uuid);

        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(productService, times(1)).deleteProduct(uuid);
    }
}
