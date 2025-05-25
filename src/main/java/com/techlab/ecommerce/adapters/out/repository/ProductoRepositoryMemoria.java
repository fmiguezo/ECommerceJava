package com.techlab.ecommerce.adapters.out.repository;

import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.exceptions.ProductoYaExistenteException;
import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoRepositoryMemoria implements IProductoRepository {
    private List<IProducto> productos = new ArrayList<>();

    @Override
    public void crear(IProducto producto) throws ProductoYaExistenteException {
        if (buscar(producto.getId()) != null) {
            throw new ProductoYaExistenteException("El producto ya existe");
        }
        if (buscar(producto.getNombre()) != null) {
            throw new ProductoYaExistenteException("El producto ya existe");
        }
        productos.add(producto);
    }

    @Override
    public List<IProducto> obtenerTodos() {
        return productos;
    }

    @Override
    public IProducto buscar(UUID id) {
        for (IProducto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public IProducto buscar(String nombre) {
        for (IProducto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public void eliminar(UUID id) {
        productos.removeIf(producto -> producto.getId().equals(id));
    }
}
