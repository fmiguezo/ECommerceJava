package com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoPersistenceMapper {

    public IProducto toDomain(ProductoEntity entity) {
        if (entity == null) {
            return null;
        }
        IProducto producto = new Producto(
                entity.getNombre(),
                entity.getPrecio(),
                entity.getStock()
        );
        producto.setId(entity.getId());
        return producto;
    }

    public ProductoEntity toEntity(IProducto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoEntity(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock()
        );
    }
}