package com.techlab.ecommerce.domain.model.pedido;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Pedido implements IPedido {
    private UUID id;
    private List<ILineaPedido> lineas;

    public Pedido(List<ILineaPedido> lineas) {
        this.id = UUID.randomUUID();
        this.lineas = lineas;
    }

    public Pedido(UUID id, List<ILineaPedido> lineas) {
        this.id = id;
        this.lineas = lineas;
    }
}