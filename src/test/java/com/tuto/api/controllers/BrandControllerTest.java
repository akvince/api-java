package com.tuto.api.controllers;

import com.tuto.api.controllers.BrandController;
import com.tuto.api.entities.BrandEntity;
import com.tuto.api.services.BrandService;
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

class BrandControllerTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBrands() {
        when(brandService.getAllBrands()).thenReturn(Collections.singletonList(new BrandEntity()));
        assertEquals(1, brandController.getAllBrands().size());
        verify(brandService, times(1)).getAllBrands();
    }

    @Test
    void createBrand() {
        BrandEntity brand = new BrandEntity();
        when(brandService.saveBrand(brand)).thenReturn(brand);
        assertEquals(brand, brandController.createBrand(brand));
        verify(brandService, times(1)).saveBrand(brand);
    }

    @Test
    void getBrandUuid() {
        UUID uuid = UUID.randomUUID();
        when(brandService.getByUuid(uuid)).thenReturn(Optional.of(new BrandEntity()));
        assertNotNull(brandController.getBrandUuid(uuid));
        verify(brandService, times(1)).getByUuid(uuid);
    }

    @Test
    void patchBrand() {
        UUID uuid = UUID.randomUUID();
        BrandEntity partialBrand = new BrandEntity();
        when(brandService.patchBrand(uuid, partialBrand)).thenReturn(Optional.of(new BrandEntity()));

        ResponseEntity<BrandEntity> responseEntity = brandController.patchBrand(uuid, partialBrand);
        assertEquals(200, responseEntity.getStatusCodeValue());

        verify(brandService, times(1)).patchBrand(uuid, partialBrand);
    }
}
