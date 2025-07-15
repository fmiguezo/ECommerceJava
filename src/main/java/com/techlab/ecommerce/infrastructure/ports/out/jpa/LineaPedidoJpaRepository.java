package com.techlab.ecommerce.infrastructure.ports.out.jpa;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.LineaPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LineaPedidoJpaRepository extends JpaRepository<LineaPedidoEntity, UUID> {
    void deleteByPedidoId(UUID pedidoId);
}