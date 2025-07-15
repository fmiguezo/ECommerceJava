package com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.LineaPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class LineaPedidoPersistenceMapper {
    private final ProductoPersistenceMapper productoMapper;

    public LineaPedidoPersistenceMapper(ProductoPersistenceMapper productoMapper) {
        this.productoMapper = productoMapper;
    }

    public ILineaPedido toDomain(LineaPedidoEntity entity) {
        if (entity == null) return null;

        ILineaPedido lineaPedido = new LineaPedido(
                productoMapper.toDomain(entity.getProducto()),
                entity.getCantidad()
        );
        lineaPedido.setId(entity.getId());
        return lineaPedido;
    }

    public LineaPedidoEntity toEntity(ILineaPedido lineaPedido) {
        if (lineaPedido == null) return null;

        LineaPedidoEntity entity = new LineaPedidoEntity();
        entity.setId(lineaPedido.getId());
        entity.setCantidad(lineaPedido.getCantidad());
        entity.setProducto(productoMapper.toEntity(lineaPedido.getProducto()));
        return entity;
    }
}