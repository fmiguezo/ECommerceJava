package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.adapters.out.repository.IPedidoRepository;
import com.techlab.ecommerce.adapters.out.repository.IProductoRepository;
import com.techlab.ecommerce.domain.exceptions.StockInsuficienteException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.model.pedido.Pedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import com.techlab.ecommerce.domain.service.producto.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrearPedidoUseCase {
    private final IProductoRepository productoRepository;
    private final IPedidoRepository pedidoRepository;

    public CrearPedidoUseCase(IProductoRepository productoRepo, IPedidoRepository pedidoRepo) {
        this.productoRepository = productoRepo;
        this.pedidoRepository = pedidoRepo;
    }

    public IPedido crearPedido(Map<UUID, Integer> productosSolicitados) throws StockInsuficienteException {
        List<ILineaPedido> lineas = new ArrayList<>();

        for (Map.Entry<UUID, Integer> entry : productosSolicitados.entrySet()) {
            UUID productoId = entry.getKey();
            int cantidad = entry.getValue();

            IProductoService productoService = new ProductoService(productoRepository);
            IProducto producto = productoService.buscarProducto(productoId)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));

            if (producto.getStock() < cantidad) {
                throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            productoService.disminuirStock(producto, cantidad);

            ILineaPedido linea = new LineaPedido(producto, cantidad);
            lineas.add(linea);
        }

        IPedido pedido = new Pedido(lineas);
        pedidoRepository.guardar(pedido);
        return pedido;
    }
}
