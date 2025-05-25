package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface JPAProductoRepository extends JpaRepository<ProductoEntity, UUID> {
    public boolean existsByNombre(String nombre);
}
