package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.service.producto.IProductoService;

import java.util.Optional;

public class ObtenerDetalleProductoUseCase {
    private final IProductoService productoService;

    public ObtenerDetalleProductoUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }

    public Optional<ProductoDTO> ejecutar(String id) {
        return productoService.buscarProducto(id)
                .map(producto -> new ProductoDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getStock()));
    }
}
