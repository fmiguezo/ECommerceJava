package com.techlab.ecommerce.domain.model.lineapedido;

import com.techlab.ecommerce.domain.exceptions.CantidadNegativaException;
import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.UUID;

public interface ILineaPedido {
    UUID getId();
    IProducto getProducto();
    int getCantidad();
    void setCantidad(int cantidad) throws CantidadNegativaException;
}
