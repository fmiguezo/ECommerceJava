package com.techlab.ecommerce.infrastructure.adapters.out.repository.jpa;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers.ProductoPersistenceMapper;
import com.techlab.ecommerce.infrastructure.ports.out.IProductoRepository;
import com.techlab.ecommerce.infrastructure.ports.out.ProductoJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// @Profile("jpa")
@Repository
@RequiredArgsConstructor
@Lazy
public class ProductoJpaRepositoryImpl implements IProductoRepository {
    private final ProductoJpaRepository jpaRepository;
    private final ProductoPersistenceMapper mapper;

    @Override
    public IProducto save(IProducto producto) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(producto)));
    }

    @Override
    public Optional<IProducto> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<IProducto> findByNombre(String nombre) {
        return jpaRepository.findByNombre(nombre).map(mapper::toDomain);
    }

    @Override
    public List<IProducto> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}