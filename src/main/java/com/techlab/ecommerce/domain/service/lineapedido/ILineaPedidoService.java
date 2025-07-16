package com.techlab.ecommerce.domain.service.lineapedido;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ILineaPedidoService {
    @Transactional(readOnly = true)
    double calcularTotal(ILineaPedido lineaPedido) throws LineaPedidoInvalidaException;

    @Transactional
    ILineaPedido crearLineaPedido(String nombreProducto, int cantidad)
            throws ProductoNoEncontradoException, StockInsuficienteException, CantidadNegativaException, LineaPedidoException;

    @Transactional
    void ajustarCantidad(ILineaPedido lineaPedido, int cantidadDelta)
            throws CantidadNegativaException, StockInsuficienteException, LineaPedidoInvalidaException, ProductoNoEncontradoException, ProductoException, LineaPedidoException;


    @Transactional
    void eliminarLinea(UUID id) throws LineaPedidoNoEncontradaException;

    @Transactional
    ILineaPedido findById(UUID id) throws LineaPedidoNoEncontradaException;
}
