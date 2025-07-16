package com.techlab.ecommerce.domain.service.lineapedido;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import com.techlab.ecommerce.domain.validators.LineaPedidoValidator;
import com.techlab.ecommerce.infrastructure.ports.out.ILineaPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LineaPedidoService implements ILineaPedidoService {

    private final ProductoService productoService;
    private final ILineaPedidoRepository lineaPedidoRepository;

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