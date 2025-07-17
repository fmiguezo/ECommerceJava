package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.LineaPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LineaPedidoJpaRepository extends JpaRepository<LineaPedidoEntity, UUID> {
    void deleteByPedidoId(UUID pedidoId);
}