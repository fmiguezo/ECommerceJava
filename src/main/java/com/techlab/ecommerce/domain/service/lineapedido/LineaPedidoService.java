package com.techlab.ecommerce.domain.service.lineapedido;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.application.mapper.LineaPedidoMapper;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import com.techlab.ecommerce.domain.validators.LineaPedidoValidator;
import com.techlab.ecommerce.infrastructure.ports.out.ILineaPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LineaPedidoService implements ILineaPedidoService {
    private ILineaPedidoRepository lineaPedidoRepository;
    private LineaPedidoMapper lineaPedidoMapper;
    private IProductoService productoService;

    @Autowired
    public void setProductoService(@Lazy IProductoService productoService) {
        this.productoService = productoService;
    }

    @Transactional(readOnly = true)
    @Override
    public double calcularTotal(ILineaPedido lineaPedido) throws LineaPedidoInvalidaException {
        LineaPedidoValidator.validarLineaPedido(lineaPedido);
        return lineaPedido.getCantidad() * lineaPedido.getProducto().getPrecio();
    }

    @Transactional
    @Override
    public ILineaPedido crearLineaPedido(String nombreProducto, int cantidad)
            throws ProductoNoEncontradoException, StockInsuficienteException, CantidadNegativaException, LineaPedidoException {
        LineaPedidoValidator.validarCantidad(cantidad);

        IProducto producto = productoService.findByNombre(nombreProducto)
                .orElseThrow(() -> new ProductoNoEncontradoException(
                        "Producto no encontrado: " + nombreProducto));

        LineaPedidoValidator.validarStock(producto, cantidad);

        return new LineaPedido(producto, cantidad);
    }

    @Transactional
    @Override
    public void ajustarCantidad(ILineaPedido lineaPedido, int cantidadDelta)
            throws CantidadNegativaException, StockInsuficienteException, LineaPedidoInvalidaException, ProductoNoEncontradoException, ProductoException, LineaPedidoException {

        LineaPedidoValidator.validarLineaPedido(lineaPedido);
        int nuevaCantidad = lineaPedido.getCantidad() + cantidadDelta;
        LineaPedidoValidator.validarCantidad(nuevaCantidad);

        if (cantidadDelta < 0) {
            LineaPedidoValidator.validarStock(lineaPedido.getProducto(), nuevaCantidad);
        }

        lineaPedido.setCantidad(nuevaCantidad);
        productoService.updateStock(
                lineaPedido.getProducto().getId(),
                -cantidadDelta
        );
    }

    @Override
    public void ajustarCantidad(LineaPedidoDTO dto, int cantidadDelta)
            throws CantidadNegativaException, StockInsuficienteException, LineaPedidoInvalidaException,
            ProductoNoEncontradoException, ProductoException, LineaPedidoException {

        IProducto producto = productoService.findByNombre(dto.getNombreProducto())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));

        ILineaPedido lineaPedido = lineaPedidoMapper.toDomain(Optional.of(dto), producto);

        ajustarCantidad(lineaPedido, cantidadDelta); // llamás al método que ya tenías
    }

    @Transactional
    @Override
    public void eliminarLinea(UUID id) throws LineaPedidoNoEncontradaException {
        if (!lineaPedidoRepository.existsById(id)) {
            throw new LineaPedidoNoEncontradaException("Linea de pedido no encontrada: " + id);
        }
        lineaPedidoRepository.deleteById(id);
    }

    @Override
    public ILineaPedido findById(UUID id) throws LineaPedidoNoEncontradaException {
        return lineaPedidoRepository.findById(id)
                .orElseThrow(() -> new LineaPedidoNoEncontradaException("Linea de pedido no encontrada: " + id));
    }
}