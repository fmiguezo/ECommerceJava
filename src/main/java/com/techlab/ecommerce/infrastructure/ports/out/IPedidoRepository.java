package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.domain.model.pedido.IPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoRepository {
    IPedido save(IPedido pedido);
    Optional<IPedido> findById(UUID id);
    List<IPedido> findAll();
    void deleteById(UUID id);
    boolean existsById(UUID id);
}
