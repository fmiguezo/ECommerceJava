package com.techlab.ecommerce.infrastructure.adapters.out.repository.jpa;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers.LineaPedidoPersistenceMapper;
import com.techlab.ecommerce.infrastructure.ports.out.ILineaPedidoRepository;
import com.techlab.ecommerce.infrastructure.ports.out.jpa.LineaPedidoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Profile("jpa")
@Component
@RequiredArgsConstructor
public class LineaPedidoJpaRepositoryImpl implements ILineaPedidoRepository {

    private final LineaPedidoJpaRepository jpaRepository;
    private final LineaPedidoPersistenceMapper mapper;

    @Override
    public ILineaPedido save(ILineaPedido lineaPedido) {  // Cambiado de 'guardar' a 'save'
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(lineaPedido)));
    }

    @Override
    public void deleteByPedidoId(UUID pedidoId) {  // Cambiado de 'eliminarPorPedidoId' a 'deleteByPedidoId'
        jpaRepository.deleteByPedidoId(pedidoId);
    }
}