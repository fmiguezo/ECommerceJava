package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface JPAPedidoRepository extends JpaRepository<PedidoEntity, UUID> {
}
