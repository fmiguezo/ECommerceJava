package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(IProducto producto) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        return dto;
    }

    public IProducto toDomain(ProductoDTO dto) {
        if (dto == null) return null;

        IProducto producto = new Producto(dto.getNombre(), dto.getPrecio(), dto.getStock());
        if (dto.getId() != null) {
            producto.setId(dto.getId());
        }
        return producto;
    }
}