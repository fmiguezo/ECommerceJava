package com.techlab.ecommerce.domain.model.pedido;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;

import java.util.List;
import java.util.UUID;

public class Pedido implements IPedido {
    private final UUID id;
    private final List<ILineaPedido> lineas;

    public Pedido(List<ILineaPedido> lineas) {
        this.id = UUID.randomUUID();
        this.lineas = lineas;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public List<ILineaPedido> getLineas() {
        return lineas;
    }
}
