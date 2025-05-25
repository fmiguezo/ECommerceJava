package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;

import java.util.List;

public class ListarProductosUseCase {
    private final IProductoService productoService;

    public ListarProductosUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }

    public List<IProducto> ejecutar() {
        return productoService.listarProductos();
    }
}
