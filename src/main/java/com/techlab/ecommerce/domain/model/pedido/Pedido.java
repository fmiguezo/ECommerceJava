package com.techlab.ecommerce.domain.model.pedido;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;

import java.util.List;
import java.util.UUID;

public class Pedido implements IPedido {
    private UUID id;
    private List<ILineaPedido> lineas;

    public Pedido(List<ILineaPedido> lineas) {
        this.id = UUID.randomUUID();
        this.lineas = lineas;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public List<ILineaPedido> getLineas() {
        return lineas;
    }

    @Override
    public void setLineas(List<ILineaPedido> lineas) {
        this.lineas = lineas;
    }
}
