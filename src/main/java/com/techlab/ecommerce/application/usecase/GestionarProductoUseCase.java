package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GestionarProductoUseCase {

    private final IProductoService productoService;

    public GestionarProductoUseCase(IProductoService productoService) {
        this.productoService = productoService;
    }

    public ProductoDTO crearProducto(String nombre, double precio, int stock) throws ProductoYaExistenteException {
        ProductoDTO productoDTO = new ProductoDTO(nombre, precio, stock);
        productoService.crearProducto(productoDTO);
        return productoDTO;
    }

    public List<ProductoDTO> listarProductos() {
        return productoService.listarProductos();
    }

    public Optional<ProductoDTO> buscarProducto(UUID id) {
        return productoService.buscarProducto(id);
    }

    public Optional<ProductoDTO> buscarProducto(String nombre) {
        return productoService.buscarProducto(nombre);
    }

    public void actualizarProducto(String nombre, double nuevoPrecio, int nuevoStock)
            throws ProductoNoEncontradoException, ProductoYaExistenteException {
        productoService.actualizarProducto(nombre, nuevoPrecio, nuevoStock);
    }

    public void eliminarProducto(UUID id) throws ProductoNoEncontradoException {
        productoService.eliminarProducto(id);
    }

    public void aumentarStock(ProductoDTO producto, int cantidad) throws CantidadNegativaException {
        productoService.aumentarStock(producto, cantidad);
    }

    public void disminuirStock(ProductoDTO producto, int cantidad) throws StockInsuficienteException {
        productoService.disminuirStock(producto, cantidad);
    }
}