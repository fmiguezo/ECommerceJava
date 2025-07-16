package com.techlab.ecommerce.domain.exceptions;

public class LineaPedidoNoEncontradaException extends RuntimeException {
    public LineaPedidoNoEncontradaException(String message) {
        super(message);
    }
}
