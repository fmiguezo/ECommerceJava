package com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.model.pedido.Pedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.LineaPedidoEntity;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoPersistenceMapper {
    private LineaPedidoPersistenceMapper lineaPedidoPersistenceMapper;
    private ProductoPersistenceMapper productoPersistenceMapper;

    public PedidoPersistenceMapper(LineaPedidoPersistenceMapper lineaPedidoPersistenceMapper, ProductoPersistenceMapper productoPersistenceMapper) {
        this.lineaPedidoPersistenceMapper = lineaPedidoPersistenceMapper;
        this.productoPersistenceMapper = productoPersistenceMapper;
    }

    @Transactional
    public IPedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;

        List<ILineaPedido> lineas = entity.getLineas().stream()
                .map(lineaPedidoPersistenceMapper::toDomain)
                .toList();

        IPedido pedido = new Pedido(lineas);
        pedido.setId(entity.getId());
        return pedido;
    }

    @Transactional
    public PedidoEntity toEntity(IPedido pedido) {
        if (pedido == null) return null;

        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setLineas(new ArrayList<>());

        for (ILineaPedido linea : pedido.getLineas()) {
            LineaPedidoEntity lineaEntity = lineaPedidoPersistenceMapper.toEntity(linea);
            entity.getLineas().add(lineaEntity);
        }

        entity.getLineas().clear();
        entity.getLineas().addAll(pedido.getLineas().stream()
                .map(lineaPedidoPersistenceMapper::toEntity)
                .toList());

        return entity;
    }
}
