package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductoRepository {
    IProducto save(IProducto producto);
    Optional<IProducto> findById(UUID id);
    Optional<IProducto> findByNombre(String nombre);
    List<IProducto> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
