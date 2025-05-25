package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;
import com.techlab.ecommerce.domain.model.producto.ProductoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoService implements IProductoService {
    private ProductoFactory productoFactory;
    private List<IProducto> productos;

    public ProductoService() {
        this.productos = new ArrayList<>();
    }

    public ProductoService(ProductoFactory productoFactory) {
        this.productoFactory = productoFactory;
        this.productos = new ArrayList<>();
    }

    @Override
    public IProducto crearProducto(String nombre, double precio, int stock) throws ProductoYaExistenteException {
        if (false == productos.isEmpty()) {
            for (IProducto producto : productos) {
                if (producto.getNombre().equalsIgnoreCase(nombre)) {
                    throw new ProductoYaExistenteException("El producto ya existe con el nombre: " + nombre);
                }
            }
        }
        IProducto nuevoProducto = new Producto(nombre, precio, stock);
        productos.add(nuevoProducto);
        return nuevoProducto;
    }

    @Override
    public void agregarProducto(String nombre, double precio, int stock) {

    }

    @Override
    public List<IProducto> listarProductos() {
        // Lógica para listar productos
        return null;
    }

    @Override
    public Optional<IProducto> buscarProducto(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<IProducto> buscarProducto(String nombre) {
        return productos.stream()
                .filter(producto -> producto.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public void actualizarProducto(int id, double nuevoPrecio, int nuevoStock) throws ProductoNoEncontradoException {
        Optional<IProducto> productoOpt = buscarProducto(id);
        if (productoOpt.isPresent()) {
            IProducto producto = productoOpt.get();
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    public void eliminarProducto(int id) throws ProductoNoEncontradoException {
        Optional<IProducto> productoOpt = buscarProducto(id);
        if (productoOpt.isPresent()) {
            productos.remove(productoOpt.get());
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    public void setProductoFactory(ProductoFactory productoFactory) {
        this.productoFactory = productoFactory;
    }

    @Override
    public ProductoFactory getProductoFactory() throws ProductFactoryNotSetException {
        if (this.productoFactory == null) {
            throw new ProductFactoryNotSetException("ProductoFactory no está configurada");
        }
        return this.productoFactory;
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
    public void mostrarDetalle(IProducto producto) {
        System.out.println("ID: " + producto.getId());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Precio: " + producto.getPrecio());
        System.out.println("Stock: " + producto.getStock());
    }

    @Override
    public void buscarYMostrarProducto(int id) throws ProductoNoEncontradoException {
        Optional<IProducto> productoOpt = buscarProducto(id);
        if (productoOpt.isPresent()) {
            mostrarDetalle(productoOpt.get());
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    public void buscarYMostrarProducto(String nombre) throws ProductoNoEncontradoException {
        Optional<IProducto> productoOpt = buscarProducto(nombre);
        if (productoOpt.isPresent()) {
            mostrarDetalle(productoOpt.get());
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con nombre: " + nombre);
        }
    }
}
