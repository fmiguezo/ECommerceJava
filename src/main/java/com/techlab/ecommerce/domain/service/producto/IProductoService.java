package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.ProductoFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductoService {
    IProducto crearProducto(String nombre, double precio, int stock) throws ProductoYaExistenteException;
    void agregarProducto(String nombre, double precio, int stock);
    List<IProducto> listarProductos();
    Optional<IProducto> buscarProducto(UUID id);
    Optional<IProducto> buscarProducto(String nombre);
    void actualizarProducto(String nombre, double nuevoPrecio, int nuevoStock) throws ProductoNoEncontradoException, ProductoYaExistenteException;
    void eliminarProducto(String nombre) throws ProductoNoEncontradoException;
    void setProductoFactory(ProductoFactory productoFactory);
    ProductoFactory getProductoFactory() throws ProductFactoryNotSetException;
    void disminuirStock(IProducto producto, int cantidad) throws StockInsuficienteException;
    void aumentarStock(IProducto producto, int cantidad) throws CantidadNegativaException;
}
