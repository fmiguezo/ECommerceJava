package com.techlab.ecommerce.domain.model.lineapedido;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
public class LineaPedido implements ILineaPedido {
    private UUID id;
    private IProducto producto;
    private int cantidad;

    public LineaPedido(IProducto producto, int cantidad) {
        this.id = UUID.randomUUID();
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public LineaPedido(UUID id, IProducto producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }
}