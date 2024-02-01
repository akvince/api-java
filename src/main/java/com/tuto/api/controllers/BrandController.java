package com.tuto.api.controllers;

import com.tuto.api.entities.BrandEntity;
import com.tuto.api.services.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get brand by uuid", response = List.class)
    public Optional<BrandEntity> getBrandUuid(@PathVariable UUID uuid) {return brandService.getByUuid(uuid);}

    @PatchMapping("/{uuid}")
    @ApiOperation(value = "Patch brand", response = List.class)
    public ResponseEntity<BrandEntity> patchBrand(
            @PathVariable UUID uuid,
            @RequestBody BrandEntity partialBrand
    ) {
        Optional<BrandEntity> updateBrandOptional = brandService.patchBrand(uuid, partialBrand);

        return updateBrandOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
