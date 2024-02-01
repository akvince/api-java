package com.tuto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuto.api.entities.ProductEntity;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
