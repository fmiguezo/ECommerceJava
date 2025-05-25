package com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.LineaPedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineaPedidoPersistenceMapper {
    @Autowired
    private ProductoPersistenceMapper productoPersistenceMapper;

    public LineaPedidoPersistenceMapper(ProductoPersistenceMapper productoPersistenceMapper) {
        this.productoPersistenceMapper = productoPersistenceMapper;
    }

    public ILineaPedido toDomain(LineaPedidoEntity entity) {
        if (entity == null) return null;

        ILineaPedido lineaPedido = new LineaPedido(
                entity.getProducto() != null ? productoPersistenceMapper.toDomain(entity.getProducto()) : null,
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
        entity.setProducto(productoPersistenceMapper.toEntity(lineaPedido.getProducto()));
        return entity;
    }
}
