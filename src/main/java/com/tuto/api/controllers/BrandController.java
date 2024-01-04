package com.tuto.api.controllers;

import com.tuto.api.entities.BrandEntity;
import com.tuto.api.services.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@Api(tags = "Brands Controller", description = "Operations pertaining to brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @ApiOperation(value = "Get all brands", response = List.class)
    public List<BrandEntity> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    @ApiOperation(value = "Create brand", response = BrandEntity.class)
    public BrandEntity createBrand(@RequestBody BrandEntity brand) {
        return brandService.saveBrand(brand);
    }
}
