package com.techlab.ecommerce.domain.service.pedido;

import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;

public interface IPedidoService {
    double calcularTotal(IPedido pedido);
    void procesarPedido(IPedido pedido);
    void setLineaPedidoService(ILineaPedidoService lineaPedidoService);
}
