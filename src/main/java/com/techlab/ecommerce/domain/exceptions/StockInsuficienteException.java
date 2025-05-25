package com.techlab.ecommerce.domain.exceptions;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
