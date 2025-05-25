package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.application.mapper.ProductoMapper;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.ProductoFactory;
import com.techlab.ecommerce.infrastructure.ports.out.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductoService implements IProductoService {
    private ProductoFactory productoFactory;
    private ProductoMapper productoMapper;
    private IProductoRepository productoRepository;

    @Autowired
    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        this.productoFactory = productoFactory;
    }

    @Override
    public IProducto crearProducto(ProductoDTO productoDTO) throws ProductoYaExistenteException {
        Optional<IProducto> productoExistente = Optional.ofNullable(productoRepository.buscar(productoDTO.getNombre()));
        if (productoExistente.isPresent()) {
            throw new ProductoYaExistenteException("El producto ya existe con el nombre: " + productoDTO.getNombre());
        }
        IProducto nuevoProducto = productoMapper.toDomain(productoDTO);
        productoRepository.crear(nuevoProducto);
        return nuevoProducto;
    }

    @Override
    public void agregarProducto(String nombre, double precio, int stock) {

    }

    @Override
    public List<ProductoDTO> listarProductos() {
        List<IProducto> productos = productoRepository.obtenerTodos();
        if (productos.isEmpty()) {
            return List.of();
        }

        return productos.stream()
                .map(productoMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ProductoDTO> buscarProducto(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        IProducto producto = productoRepository.buscar(id);
        ProductoDTO productoDTO = productoMapper.toDTO(producto);

        return Optional.ofNullable(productoDTO);
    }

    @Override
    public Optional<ProductoDTO> buscarProducto(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return Optional.empty();
        }
        IProducto producto = productoRepository.buscar(nombre);
        if (producto == null) {
            return Optional.empty();
        }
        ProductoDTO productoDTO = productoMapper.toDTO(producto);
        return Optional.of(productoDTO);
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
