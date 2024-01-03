package com.tuto.api.services;

import com.tuto.api.entities.ProductEntity;
import com.tuto.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public Optional<ProductEntity> getByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid);
    }

    public Optional<ProductEntity> patchProduct(UUID uuid, ProductEntity partialProduct) {
        Optional<ProductEntity> existingProductOptional = getByUuid(uuid);

        if (existingProductOptional.isPresent()) {
            ProductEntity existingProduct = existingProductOptional.get();

            // Appliquez les modifications partielles
            if (partialProduct.getName() != null) {
                existingProduct.setName(partialProduct.getName());
            }

            if (partialProduct.getPrice() != null) {
                existingProduct.setPrice(partialProduct.getPrice());
            }

            ProductEntity updatedProduct = productRepository.save(existingProduct);
            return Optional.of(updatedProduct);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteProduct(UUID uuid) {
        try {
            productRepository.deleteByUuid(uuid);
            return true;
        } catch (EmptyResultDataAccessException e) {
            // Si le produit n'est pas trouvé dans la base de données
            return false;
        }
    }
}

