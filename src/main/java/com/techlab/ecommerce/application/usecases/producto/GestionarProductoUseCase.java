package com.techlab.ecommerce.application.usecases.producto;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.application.mapper.ProductoMapper;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GestionarProductoUseCase {

    private final IProductoService productoService;
    private final ProductoMapper productoMapper;

    @Autowired
    public GestionarProductoUseCase(@Lazy IProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @Transactional
    public ProductoDTO crearProducto(ProductoDTO productoDTO) throws ProductoException {
        IProducto producto = productoMapper.toDomain(productoDTO);
        IProducto productoGuardado = productoService.save(producto);
        return productoMapper.toDTO(productoGuardado);
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarProductos() {
        return productoService.findAll().stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ProductoDTO> buscarProductoPorId(UUID id) {
        return productoService.findById(id)
                .map(productoMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<ProductoDTO> buscarProductoPorNombre(String nombre) {
        return productoService.findByNombre(nombre)
                .map(productoMapper::toDTO);
    }

    @Transactional
    public ProductoDTO actualizarProducto(UUID id, ProductoDTO productoDTO)
            throws ProductoNoEncontradoException, ProductoException {

        // Verificar existencia
        if (!productoService.existsById(id)) {
            throw new ProductoNoEncontradoException(id.toString());
        }

        IProducto producto = productoMapper.toDomain(productoDTO);
        producto.setId(id); // Asegurar que usamos el ID correcto
        IProducto productoActualizado = productoService.save(producto);

        return productoMapper.toDTO(productoActualizado);
    }

    @Transactional
    public void eliminarProducto(UUID id) throws ProductoNoEncontradoException {
        if (!productoService.existsById(id)) {
            throw new ProductoNoEncontradoException(id.toString());
        }
        productoService.deleteById(id);
    }

    @Transactional
    public ProductoDTO ajustarStock(UUID productoId, int cantidad)
            throws StockInsuficienteException, ProductoNoEncontradoException, ProductoException {

        IProducto productoActualizado = productoService.updateStock(productoId, cantidad);
        return productoMapper.toDTO(productoActualizado);
    }
}