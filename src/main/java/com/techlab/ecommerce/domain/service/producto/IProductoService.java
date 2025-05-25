package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.ProductoFactory;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    IProducto crearProducto(String nombre, double precio, int stock) throws ProductoYaExistenteException;
    void agregarProducto(String nombre, double precio, int stock);
    List<IProducto> listarProductos();
    Optional<IProducto> buscarProducto(int id);
    Optional<IProducto> buscarProducto(String nombre);
    void actualizarProducto(int id, double nuevoPrecio, int nuevoStock) throws ProductoNoEncontradoException;
    void eliminarProducto(int id) throws ProductoNoEncontradoException;
    void setProductoFactory(ProductoFactory productoFactory);
    ProductoFactory getProductoFactory() throws ProductFactoryNotSetException;
    void disminuirStock(IProducto producto, int cantidad) throws StockInsuficienteException;
    void aumentarStock(IProducto producto, int cantidad) throws CantidadNegativaException;
    void mostrarDetalle(IProducto producto);
    public void buscarYMostrarProducto(int id) throws ProductoNoEncontradoException;
    public void buscarYMostrarProducto(String nombre) throws ProductoNoEncontradoException;
}
