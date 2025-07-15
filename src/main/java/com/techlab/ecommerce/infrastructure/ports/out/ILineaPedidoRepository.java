package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;

import java.util.UUID;

public interface ILineaPedidoRepository {
    ILineaPedido save(ILineaPedido lineaPedido);
    void deleteByPedidoId(UUID pedidoId);
}
