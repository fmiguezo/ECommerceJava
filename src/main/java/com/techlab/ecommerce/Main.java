package com.techlab.ecommerce;

import com.techlab.ecommerce.adapters.in.CLIMenu;
import com.techlab.ecommerce.adapters.out.repository.PedidoRepositoryMemoria;
import com.techlab.ecommerce.adapters.out.repository.ProductoRepositoryMemoria;
import com.techlab.ecommerce.application.usecase.*;
import com.techlab.ecommerce.domain.service.producto.ProductoService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear repositorios (en memoria o reales)
        var productoRepository = new ProductoRepositoryMemoria();
        var pedidoRepository = new PedidoRepositoryMemoria();

        // Crear servicios de dominio
        var productoService = new ProductoService(productoRepository);

        // Crear casos de uso
        var gestionarProductoUseCase = new GestionarProductoUseCase(productoService);
        var crearPedidoUseCase = new CrearPedidoUseCase(productoRepository, pedidoRepository);
        var listarPedidosUseCase = new ListarPedidosUseCase(pedidoRepository);
        var listarProductosUseCase = new ListarProductosUseCase(productoService);
        var obtenerDetalleProductoUseCase = new ObtenerDetalleProductoUseCase(productoService);

        // Crear e iniciar menú CLI
        CLIMenu menu = new CLIMenu(
                gestionarProductoUseCase,
                crearPedidoUseCase,
                listarPedidosUseCase,
                listarProductosUseCase,
                obtenerDetalleProductoUseCase
        );

        menu.mostrarMenu();
    }
}