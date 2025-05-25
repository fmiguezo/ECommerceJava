package com.techlab.ecommerce.domain.service.pedido;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;

import java.util.List;

public class PedidoService implements IPedidoService {
    private ILineaPedidoService lineaPedidoService;

    public PedidoService(ILineaPedidoService lineaPedidoService) {
        this.lineaPedidoService = lineaPedidoService;
    }

    @Override
    public double calcularTotal(IPedido pedido) {
        List<ILineaPedido> lineas = pedido.getLineas();

        return lineas.stream()
                .mapToDouble(linea -> lineaPedidoService.calcularTotal(linea))
                .sum();

    }

    @Override
    public void procesarPedido(IPedido pedido) {
        List<ILineaPedido> lineas = pedido.getLineas();

        for (ILineaPedido linea : lineas) {
            try {
                lineaPedidoService.validarStock(linea);
                lineaPedidoService.decrementarCantidad(linea);
            } catch (Exception e) {
                System.err.println("Error procesando la línea de pedido: " + e.getMessage());
            }
        }
    }

    @Override
    public void setLineaPedidoService(ILineaPedidoService lineaPedidoService) {
        this.lineaPedidoService = lineaPedidoService;
    }
}
