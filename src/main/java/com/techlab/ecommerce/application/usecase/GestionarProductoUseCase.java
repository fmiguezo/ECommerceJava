package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GestionarProductoUseCase {

    private final IProductoService productoService;

    public GestionarProductoUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }

    public IProducto crearProducto(String nombre, double precio, int stock) throws ProductoYaExistenteException {
        return productoService.crearProducto(nombre, precio, stock);
    }

    public List<IProducto> listarProductos() {
        return productoService.listarProductos();
    }

    public Optional<IProducto> buscarProductoPorId(UUID id) {
        return productoService.buscarProducto(id);
    }

    public Optional<IProducto> buscarProductoPorNombre(String nombre) {
        return productoService.buscarProducto(nombre);
    }

    public void actualizarProducto(String nombre, double nuevoPrecio, int nuevoStock)
            throws ProductoNoEncontradoException, ProductoYaExistenteException {
        productoService.actualizarProducto(nombre, nuevoPrecio, nuevoStock);
    }

    public void eliminarProducto(UUID id) throws ProductoNoEncontradoException {
        productoService.eliminarProducto(id);
    }

    public void aumentarStock(IProducto producto, int cantidad) throws CantidadNegativaException {
        productoService.aumentarStock(producto, cantidad);
    }

    public void disminuirStock(IProducto producto, int cantidad) throws StockInsuficienteException {
        productoService.disminuirStock(producto, cantidad);
    }
}