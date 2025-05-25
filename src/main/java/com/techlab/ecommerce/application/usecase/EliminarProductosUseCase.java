package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.service.producto.IProductoService;

import java.util.UUID;

public class EliminarProductosUseCase {
    private final IProductoService productoService;

    public EliminarProductosUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }

    public void ejecutar(UUID id) throws ProductoNoEncontradoException {
        productoService.eliminarProducto(id);
    }
}
