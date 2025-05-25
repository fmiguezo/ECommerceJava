package com.techlab.ecommerce.domain.model.lineapedido;

import com.techlab.ecommerce.domain.exceptions.CantidadNegativaException;
import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.UUID;

public class LineaPedido implements ILineaPedido {
    private final UUID id;
    private final IProducto producto;
    private int cantidad;

    public LineaPedido(IProducto producto, int cantidad) {
        this.id = UUID.randomUUID();
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public IProducto getProducto() {
        return producto;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public void setCantidad(int cantidad) throws CantidadNegativaException {
        if (cantidad < 0) {
            throw new CantidadNegativaException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
    }
}
