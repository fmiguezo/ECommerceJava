package com.techlab.ecommerce.application.usecases.lineapedido;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestionarLineaPedidoUseCase {

    private final ILineaPedidoService lineaPedidoService;

    @Transactional
    public ILineaPedido crearLineaPedido(String nombreProducto, int cantidad)
            throws ProductoNoEncontradoException, StockInsuficienteException,
            CantidadNegativaException, LineaPedidoException {
        return lineaPedidoService.crearLineaPedido(nombreProducto, cantidad);
    }

    @Transactional
    public void ajustarCantidad(ILineaPedido lineaPedido, int cantidadDelta)
            throws CantidadNegativaException, StockInsuficienteException,
            LineaPedidoInvalidaException, ProductoNoEncontradoException, ProductoException, LineaPedidoException {
        lineaPedidoService.ajustarCantidad(lineaPedido, cantidadDelta);
    }

    @Transactional(readOnly = true)
    public double calcularTotal(ILineaPedido lineaPedido) throws LineaPedidoInvalidaException {
        return lineaPedidoService.calcularTotal(lineaPedido);
    }
}