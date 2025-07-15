package com.techlab.ecommerce.domain.validators;

import com.techlab.ecommerce.domain.exceptions.ProductoException;

public final class ProductoValidator {
    public static void validarNombre(String nombre) throws ProductoException {
        if (nombre == null || nombre.isBlank()) {
            throw new ProductoException("El nombre del producto no puede estar vacío");
        }
    }

    public static void validarStock(int stock) throws ProductoException {
        if (stock < 0) {
            throw new ProductoException("El stock no puede ser negativo");
        }
    }
}