package com.techlab.ecommerce.infrastructure.adapters.out.repository.jpa;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers.LineaPedidoPersistenceMapper;
import com.techlab.ecommerce.infrastructure.ports.out.ILineaPedidoRepository;
import com.techlab.ecommerce.infrastructure.ports.out.LineaPedidoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


// @Profile("jpa")
@Repository
@RequiredArgsConstructor
@Lazy
public class LineaPedidoJpaRepositoryImpl implements ILineaPedidoRepository {
    private final LineaPedidoJpaRepository jpaRepository;
    private final LineaPedidoPersistenceMapper mapper;

    @Override
    public ILineaPedido save(ILineaPedido lineaPedido) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(lineaPedido)));
    }

    @Override
    public void deleteByPedidoId(UUID pedidoId) {  // Cambiado de 'eliminarPorPedidoId' a 'deleteByPedidoId'
        jpaRepository.deleteByPedidoId(pedidoId);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<ILineaPedido> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}