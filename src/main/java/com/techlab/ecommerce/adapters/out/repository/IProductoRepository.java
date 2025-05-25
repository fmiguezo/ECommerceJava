package com.techlab.ecommerce.adapters.out.repository;

import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.exceptions.ProductoYaExistenteException;
import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.List;
import java.util.UUID;

public interface IProductoRepository {
    void crear(IProducto producto) throws ProductoYaExistenteException;
    List<IProducto> obtenerTodos();
    IProducto buscar(UUID id);
    IProducto buscar(String nombre);
    void eliminar(UUID id);
}
