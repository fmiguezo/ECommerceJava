package com.techlab.ecommerce;

import com.techlab.ecommerce.application.mapper.LineaPedidoMapper;
import com.techlab.ecommerce.application.mapper.PedidoMapper;
import com.techlab.ecommerce.application.mapper.ProductoMapper;
import com.techlab.ecommerce.application.usecases.lineapedido.GestionarLineaPedidoUseCase;
import com.techlab.ecommerce.application.usecases.pedido.CrearPedidoUseCase;
import com.techlab.ecommerce.application.usecases.pedido.ListarPedidosUseCase;
import com.techlab.ecommerce.application.usecases.producto.GestionarProductoUseCase;
import com.techlab.ecommerce.application.usecases.producto.ListarProductosUseCase;
import com.techlab.ecommerce.application.usecases.producto.ObtenerDetalleProductoUseCase;
import com.techlab.ecommerce.domain.service.lineapedido.LineaPedidoService;
import com.techlab.ecommerce.domain.service.pedido.PedidoService;
import com.techlab.ecommerce.infrastructure.adapters.in.CLIMenu;
import com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria.RepositoryMemoriaPedido;
import com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria.RepositoryMemoriaProducto;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import com.techlab.ecommerce.infrastructure.ports.out.IPedidoRepository;
import com.techlab.ecommerce.infrastructure.ports.out.IProductoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
 /*       SpringApplication.run(DemoApplication.class, args) ;*/
        // 1. Configurar repositorios (en memoria)
        IProductoRepository productoRepository = new RepositoryMemoriaProducto();
        IPedidoRepository pedidoRepository = new RepositoryMemoriaPedido();

        // 2. Configurar servicios de dominio
        ProductoService productoService = new ProductoService(productoRepository);
        LineaPedidoService lineaPedidoService = new LineaPedidoService(productoService);
        PedidoService pedidoService = new PedidoService(lineaPedidoService, pedidoRepository);

        // 3. Configurar mappers
        ProductoMapper productoMapper = new ProductoMapper();
        LineaPedidoMapper lineaPedidoMapper = new LineaPedidoMapper();
        PedidoMapper pedidoMapper = new PedidoMapper(lineaPedidoMapper);

        // 4. Configurar casos de uso
        GestionarProductoUseCase gestionarProductoUseCase = new GestionarProductoUseCase(productoService, productoMapper);
        GestionarLineaPedidoUseCase gestionarLineaPedidoUseCase = new GestionarLineaPedidoUseCase(lineaPedidoService);
        CrearPedidoUseCase crearPedidoUseCase = new CrearPedidoUseCase(gestionarLineaPedidoUseCase, pedidoService, productoService);
        ListarPedidosUseCase listarPedidosUseCase = new ListarPedidosUseCase(pedidoService, pedidoMapper);
        ListarProductosUseCase listarProductosUseCase = new ListarProductosUseCase(productoService, productoMapper);
        ObtenerDetalleProductoUseCase obtenerDetalleProductoUseCase = new ObtenerDetalleProductoUseCase(productoService, productoMapper);

        // 5. Crear e iniciar menú CLI
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