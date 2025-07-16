package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;

import java.util.Optional;
import java.util.UUID;

public interface ILineaPedidoRepository {
    ILineaPedido save(ILineaPedido lineaPedido);
    void deleteByPedidoId(UUID pedidoId);
    boolean existsById(UUID id);
    void deleteById(UUID id);
    Optional<ILineaPedido> findById(UUID id);
}
