package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductoService {
    @Transactional(readOnly = true)
    List<IProducto> findAll();

    @Transactional(readOnly = true)
    Optional<IProducto> findByNombre(String nombre);

    @Transactional
    IProducto updateStock(UUID productoId, int cantidad)
            throws StockInsuficienteException, ProductoNoEncontradoException, ProductoException;

    @Transactional(readOnly = true)
    Optional<IProducto> findById(UUID id);

    @Transactional
    IProducto save(IProducto producto) throws ProductoException;

    @Transactional(readOnly = true)
    boolean existsById(UUID id);

    @Transactional
    void deleteById(UUID id) throws ProductoNoEncontradoException;
}
