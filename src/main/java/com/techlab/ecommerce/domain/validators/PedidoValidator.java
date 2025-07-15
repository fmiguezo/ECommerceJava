package com.techlab.ecommerce.domain.validators;

import com.techlab.ecommerce.domain.exceptions.PedidoException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;

import java.util.List;

public class PedidoValidator {
    public static void validar(IPedido pedido) throws PedidoException {
        if (pedido == null || pedido.getLineas() == null || pedido.getLineas().isEmpty()) {
            throw new PedidoException("Pedido inválido o sin líneas");
        }
    }

    public static void validarLineas(List<ILineaPedido> lineas) throws PedidoException {
        if (lineas == null || lineas.isEmpty()) {
            throw new PedidoException("El pedido debe contener al menos una línea");
        }
    }
}