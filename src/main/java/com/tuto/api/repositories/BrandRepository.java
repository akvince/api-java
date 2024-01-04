package com.tuto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuto.api.entities.BrandEntity;

import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
