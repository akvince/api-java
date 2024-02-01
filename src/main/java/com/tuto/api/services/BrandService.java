package com.tuto.api.services;

import com.tuto.api.entities.BrandEntity;
import com.tuto.api.entities.ProductEntity;
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

    public Optional<BrandEntity> getByUuid(UUID uuid) {
        return brandRepository.findByUuid(uuid);
    }

    public Optional<BrandEntity> patchBrand(UUID uuid, BrandEntity partialBrand) {
        Optional<BrandEntity> existingBrandOptional = getByUuid(uuid);

        if (existingBrandOptional.isPresent()) {
            BrandEntity existingBrand = existingBrandOptional.get();

            // Appliquez les modifications partielles
            if (partialBrand.getName() != null) {
                existingBrand.setName(partialBrand.getName());
            }

            // Ajoutez d'autres propriétés à mettre à jour en fonction de votre besoin

            BrandEntity updatedBrand = brandRepository.save(existingBrand);
            return Optional.of(updatedBrand);
        } else {
            return Optional.empty();
        }
    }

}
