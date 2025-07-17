package com.techlab.ecommerce.application.usecases.pedido;

import com.techlab.ecommerce.application.usecases.lineapedido.GestionarLineaPedidoUseCase;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;
import com.techlab.ecommerce.domain.service.pedido.IPedidoService;

import com.techlab.ecommerce.domain.service.producto.IProductoService;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CrearPedidoUseCase {
    private final GestionarLineaPedidoUseCase gestionarLineaPedidoUseCase;
    private IPedidoService pedidoService;
    private IProductoService productoService;
    private final ILineaPedidoService lineaPedidoService;

    @Autowired
    public CrearPedidoUseCase(GestionarLineaPedidoUseCase gestionarLineaPedidoUseCase, @Lazy IPedidoService pedidoService, ILineaPedidoService lineaPedidoService) {
        this.gestionarLineaPedidoUseCase = gestionarLineaPedidoUseCase;
        this.pedidoService = pedidoService;
        this.lineaPedidoService = lineaPedidoService;
    }


    public IPedido ejecutar(Map<UUID, Integer> productosSolicitados)
            throws StockInsuficienteException, ProductoNoEncontradoException,
            CantidadNegativaException, LineaPedidoException, PedidoException {

        List<ILineaPedido> lineas = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : productosSolicitados.entrySet()) {
            String nombreProducto = String.valueOf(entry.getKey());
            int cantidad = entry.getValue();

            ILineaPedido linea = gestionarLineaPedidoUseCase.crearLineaPedido(nombreProducto, cantidad);
            lineas.add(linea);
        }

        return pedidoService.crearPedido(lineas);
    }
}