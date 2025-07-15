package com.techlab.ecommerce.domain.validators;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;

public final class LineaPedidoValidator {

    public static void validarLineaPedido(ILineaPedido lineaPedido) throws LineaPedidoInvalidaException {
        if (lineaPedido == null || lineaPedido.getProducto() == null) {
            throw new LineaPedidoInvalidaException("La línea de pedido no puede ser nula");
        }
    }

    public static void validarCantidad(int cantidad) throws CantidadNegativaException {
        if (cantidad <= 0) {
            throw new CantidadNegativaException("La cantidad debe ser mayor a cero");
        }
    }

    public static void validarStock(IProducto producto, int cantidad) throws StockInsuficienteException {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException(
                    String.format("Stock insuficiente para %s. Disponible: %d, Solicitado: %d",
                            producto.getNombre(),
                            producto.getStock(),
                            cantidad)
            );
        }
    }
}