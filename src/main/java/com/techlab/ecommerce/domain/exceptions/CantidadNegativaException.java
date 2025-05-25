package com.techlab.ecommerce.domain.exceptions;

public class CantidadNegativaException extends Exception {
    public CantidadNegativaException(String message) {
        super(message);
    }
}
