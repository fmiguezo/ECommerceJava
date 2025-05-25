package com.techlab.ecommerce.domain.exceptions;

public class ProductoYaExistenteException extends Exception {
    public ProductoYaExistenteException(String message) {
        super(message);
    }
}
