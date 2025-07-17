package com.techlab.ecommerce.domain.service.producto;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.validators.ProductoValidator;
import com.techlab.ecommerce.infrastructure.ports.out.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<IProducto> findByNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return Optional.empty();
        }
        return productoRepository.findByNombre(nombre);
    }

    @Transactional
    @Override
    public IProducto updateStock(UUID productoId, int cantidad)
            throws StockInsuficienteException, ProductoNoEncontradoException, ProductoException {

        IProducto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ProductoNoEncontradoException(productoId.toString()));

        int nuevoStock = producto.getStock() + cantidad;
        ProductoValidator.validarStock(nuevoStock);

        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<IProducto> findById(UUID id) {
        return productoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<IProducto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional
    @Override
    public IProducto save(IProducto producto) throws ProductoException {
        ProductoValidator.validarNombre(producto.getNombre());
        ProductoValidator.validarStock(producto.getStock());
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(UUID id) {
        if (id == null) {
            return false;
        }
        return productoRepository.existsById(id);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) throws ProductoNoEncontradoException {
        if (!existsById(id)) {
            throw new ProductoNoEncontradoException(id.toString());
        }
        productoRepository.deleteById(id);
    }
}