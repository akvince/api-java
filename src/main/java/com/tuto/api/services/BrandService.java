package com.tuto.api.services;

import com.tuto.api.entities.BrandEntity;
import com.tuto.api.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }

    public BrandEntity saveBrand(BrandEntity brand) {
        return brandRepository.save(brand);
    }
}
