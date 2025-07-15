package com.techlab.ecommerce.infrastructure.ports.out.jpa;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, UUID> {
    Optional<ProductoEntity> findByNombre(String nombre);
}