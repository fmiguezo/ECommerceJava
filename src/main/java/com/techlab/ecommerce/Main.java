package com.techlab.ecommerce;

import com.techlab.ecommerce.infrastructure.adapters.in.CLIMenu;
import com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria.RepositoryMemoriaPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria.RepositoryMemoriaProducto;
import com.techlab.ecommerce.application.usecase.*;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args)
        // Crear repositorios (en memoria o reales)
        var productoRepository = new RepositoryMemoriaProducto();
        var pedidoRepository = new RepositoryMemoriaPedido();

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