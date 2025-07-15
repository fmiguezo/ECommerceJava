package com.techlab.ecommerce.domain.service.pedido;

import com.techlab.ecommerce.domain.exceptions.PedidoException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.model.pedido.Pedido;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPedidoService {

    @Transactional
    Pedido crearPedido(List<ILineaPedido> lineas) throws PedidoException;

    @Transactional(readOnly = true)
    double calcularTotal(IPedido pedido);

    @Transactional
    void procesarPedido(IPedido pedido) throws PedidoException;
}
