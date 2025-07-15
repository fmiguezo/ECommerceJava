package com.techlab.ecommerce.infrastructure.ports.out.jpa;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, UUID> {

    @Query("SELECT p FROM PedidoEntity p WHERE SIZE(p.lineas) > :minLineas")
    List<PedidoEntity> buscarPedidosConLineas(int minLineas);
}