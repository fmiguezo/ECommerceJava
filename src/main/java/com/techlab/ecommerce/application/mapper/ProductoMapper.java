package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.model.producto.IProducto;

public class ProductoMapper {

    public static ProductoDTO toDTO(IProducto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock()
        );
    }
}