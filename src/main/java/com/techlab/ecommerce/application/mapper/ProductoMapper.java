package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;

public class ProductoMapper {

    public ProductoDTO toDTO(IProducto producto) {
        ProductoDTO productoDTO = new ProductoDTO(producto.getNombre(), producto.getPrecio(), producto.getStock());
        productoDTO.setId(producto.getId());
        return productoDTO;
    }

    public static IProducto toDomain(ProductoDTO productoDTO) {
        return new Producto(
                productoDTO.getNombre(),
                productoDTO.getPrecio(),
                productoDTO.getStock()
        );
    }
}