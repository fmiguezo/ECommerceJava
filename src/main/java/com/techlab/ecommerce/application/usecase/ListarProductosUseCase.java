package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;

import java.util.List;

public class ListarProductosUseCase {
    private final IProductoService productoService;

    public ListarProductosUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }

    public List<ProductoDTO> ejecutar() {
        List <IProducto> productos = productoService.listarProductos();
        return productos.stream()
                .map(producto -> new ProductoDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getStock()))
                .toList();
    }
}
