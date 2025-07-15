package com.techlab.ecommerce.infrastructure.adapters.out.repository.jpa;

import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers.PedidoPersistenceMapper;
import com.techlab.ecommerce.infrastructure.ports.out.IPedidoRepository;
import com.techlab.ecommerce.infrastructure.ports.out.jpa.PedidoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("jpa")
@Component
@RequiredArgsConstructor
public class PedidoJpaRepositoryImpl implements IPedidoRepository {
    private final PedidoJpaRepository jpaRepository;
    private final PedidoPersistenceMapper mapper;

    @Override
    public IPedido save(IPedido pedido) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(pedido)));
    }

    @Override
    public Optional<IPedido> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<IPedido> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}