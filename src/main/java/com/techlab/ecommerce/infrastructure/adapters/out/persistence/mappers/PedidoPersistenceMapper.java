package com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.model.pedido.Pedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.LineaPedidoEntity;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoPersistenceMapper {
    private final LineaPedidoPersistenceMapper lineaPedidoMapper;

    public PedidoPersistenceMapper(LineaPedidoPersistenceMapper lineaPedidoMapper) {
        this.lineaPedidoMapper = lineaPedidoMapper;
    }

    public IPedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;

        List<ILineaPedido> lineas = entity.getLineas().stream()
                .map(lineaPedidoMapper::toDomain)
                .collect(Collectors.toList());

        IPedido pedido = new Pedido(lineas);
        pedido.setId(entity.getId());
        return pedido;
    }

    public PedidoEntity toEntity(IPedido pedido) {
        if (pedido == null) return null;

        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());

        List<LineaPedidoEntity> lineasEntities = pedido.getLineas().stream()
                .map(linea -> {
                    LineaPedidoEntity lineaEntity = lineaPedidoMapper.toEntity(linea);
                    lineaEntity.setPedido(entity); // Establece la relación bidireccional
                    return lineaEntity;
                })
                .collect(Collectors.toList());

        entity.setLineas(lineasEntities);
        return entity;
    }
}