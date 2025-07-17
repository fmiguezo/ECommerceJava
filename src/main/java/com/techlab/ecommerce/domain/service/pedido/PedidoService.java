package com.techlab.ecommerce.domain.service.pedido;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.model.pedido.Pedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;
import com.techlab.ecommerce.domain.validators.PedidoValidator;
import com.techlab.ecommerce.infrastructure.ports.out.IPedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {
    private final ILineaPedidoService lineaPedidoService;
    private final IPedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<IPedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional
    @Override
    public Pedido crearPedido(List<ILineaPedido> lineas) throws PedidoException {
        PedidoValidator.validarLineas(lineas);
        return new Pedido(lineas);
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public double calcularTotal(IPedido pedido) {
        PedidoValidator.validar(pedido);
        return pedido.getLineas().stream()
                .mapToDouble(lineaPedidoService::calcularTotal)
                .sum();
    }

    @Transactional
    @Override
    public void procesarPedido(IPedido pedido) throws PedidoException {
        PedidoValidator.validar(pedido);
        pedido.getLineas().forEach(linea ->
                {
                    try {
                        lineaPedidoService.ajustarCantidad(linea, -linea.getCantidad());
                    } catch (CantidadNegativaException | LineaPedidoException | ProductoException |
                             ProductoNoEncontradoException | StockInsuficienteException | LineaPedidoInvalidaException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public Optional<IPedido> findById(UUID id) {
        return pedidoRepository.findById(id);
    }
}