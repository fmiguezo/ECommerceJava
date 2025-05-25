package com.techlab.ecommerce.infrastructure.ports.out;

import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.exceptions.ProductoYaExistenteException;
import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductoRepository {
    void crear(IProducto producto) throws ProductoYaExistenteException;
    List<IProducto> obtenerTodos();
    Optional<IProducto> buscar(UUID id);
    Optional<IProducto> buscar(String nombre);
    void eliminar(UUID id);
}
