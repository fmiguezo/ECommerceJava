package com.techlab.ecommerce.application.usecases.lineapedido;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.application.mapper.LineaPedidoMapper;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GestionarLineaPedidoUseCase {

    private final ILineaPedidoService lineaPedidoService;
    private final LineaPedidoMapper lineaPedidoMapper;

    @Transactional
    public ILineaPedido crearLineaPedido(String nombreProducto, int cantidad)
            throws ProductoNoEncontradoException, StockInsuficienteException,
            CantidadNegativaException, LineaPedidoException {
        return lineaPedidoService.crearLineaPedido(nombreProducto, cantidad);
    }

    @Transactional
    public LineaPedidoDTO ajustarCantidad(LineaPedidoDTO lineaPedido, int cantidadDelta)
            throws CantidadNegativaException, StockInsuficienteException,
            LineaPedidoInvalidaException, ProductoNoEncontradoException, ProductoException, LineaPedidoException {
        lineaPedidoService.ajustarCantidad(lineaPedido, cantidadDelta);
        return lineaPedido;
    }

    @Transactional(readOnly = true)
    public double calcularTotal(ILineaPedido lineaPedido) throws LineaPedidoInvalidaException {
        return lineaPedidoService.calcularTotal(lineaPedido);
    }

    @Transactional
    public void eliminarLinea(UUID id) throws LineaPedidoNoEncontradaException {
        lineaPedidoService.eliminarLinea(id);
    }

    @Transactional(readOnly = true)
    public LineaPedidoDTO buscarLineaPorId(UUID id) throws LineaPedidoNoEncontradaException {
        ILineaPedido lineaPedido = lineaPedidoService.findById(id);
        return lineaPedidoMapper.toDTO(lineaPedido);
    }
}