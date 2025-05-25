package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;

import java.util.Optional;
import java.util.UUID;

public class ObtenerDetalleProductoUseCase {
    private final IProductoService productoService;

    public ObtenerDetalleProductoUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }
    public ProductoDTO ejecutar(UUID id) {
        try {
            Optional<IProducto> productoOpt = productoService.buscarProducto(id);
            if (productoOpt.isPresent()) {
                IProducto producto = productoOpt.get();
                return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
