package com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoPersistenceMapper {

    public IProducto toDomain(ProductoEntity productoEntity) {
        if (productoEntity == null) {
            return null;
        }

        IProducto producto = new Producto(productoEntity.getNombre(), productoEntity.getPrecio(), productoEntity.getStock());
        producto.setId(productoEntity.getId());

        return producto;
    }

    public ProductoEntity toEntity(IProducto producto) {
        if (producto == null) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setId(producto.getId());
        productoEntity.setNombre(producto.getNombre());
        productoEntity.setPrecio(producto.getPrecio());
        productoEntity.setStock(producto.getStock());

        return productoEntity;
    }
}
