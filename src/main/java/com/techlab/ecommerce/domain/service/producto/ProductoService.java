package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.adapters.out.repository.IProductoRepository;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;
import com.techlab.ecommerce.domain.model.producto.ProductoFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductoService implements IProductoService {
    private ProductoFactory productoFactory;
    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        this.productoFactory = new ProductoFactory();
    }

    public ProductoService(ProductoFactory productoFactory, IProductoRepository productoRepository) {
        this.productoFactory = productoFactory;
        this.productoRepository = productoRepository;
    }

    @Override
    public IProducto crearProducto(String nombre, double precio, int stock) throws ProductoYaExistenteException {
        Optional<IProducto> productoExistente = Optional.ofNullable(productoRepository.buscar(nombre));
        if (productoExistente.isPresent()) {
            throw new ProductoYaExistenteException("El producto ya existe con el nombre: " + nombre);
        }
        IProducto nuevoProducto = new Producto(nombre, precio, stock);
        productoRepository.crear(nuevoProducto);
        return nuevoProducto;
    }

    @Override
    public void agregarProducto(String nombre, double precio, int stock) {

    }

    @Override
    public List<IProducto> listarProductos() {
        return productoRepository.obtenerTodos();
    }

    @Override
    public Optional<IProducto> buscarProducto(UUID id) {
        return Optional.ofNullable(productoRepository.buscar(id));
    }

    @Override
    public Optional<IProducto> buscarProducto(String nombre) {
        return Optional.ofNullable(productoRepository.buscar(nombre));
    }

    @Override
    public void actualizarProducto(String nombre, double nuevoPrecio, int nuevoStock) throws ProductoNoEncontradoException, ProductoYaExistenteException {
        Optional<IProducto> productoOpt = buscarProducto(nombre);
        if (productoOpt.isPresent()) {
            IProducto producto = productoOpt.get();
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con nombre: " + nombre);
        }
    }

    @Override
    public void eliminarProducto(UUID id) throws ProductoNoEncontradoException {
        Optional<IProducto> productoOpt = buscarProducto(id);
        if (productoOpt.isPresent()) {
            IProducto producto = productoOpt.get();
            productoRepository.eliminar(producto.getId());
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con id: " + id);
        }
    }

    @Override
    public void disminuirStock(IProducto producto, int cantidad) throws StockInsuficienteException {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para disminuir");
        }
        producto.setStock(producto.getStock() - cantidad);
    }

    @Override
    public void aumentarStock(IProducto producto, int cantidad) throws CantidadNegativaException {
        if (cantidad < 0) {
            throw new CantidadNegativaException("La cantidad a aumentar no puede ser negativa");
        }
        producto.setStock(producto.getStock() + cantidad);
    }

    @Override
    public void setProductoFactory(ProductoFactory productoFactory) {
        this.productoFactory = productoFactory;
    }

    @Override
    public ProductoFactory getProductoFactory() throws ProductFactoryNotSetException {
        return productoFactory;
    }
}
