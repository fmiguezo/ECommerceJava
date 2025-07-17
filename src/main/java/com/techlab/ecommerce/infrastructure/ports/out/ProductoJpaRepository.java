package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, UUID> {
    Optional<ProductoEntity> findByNombre(String nombre);
}