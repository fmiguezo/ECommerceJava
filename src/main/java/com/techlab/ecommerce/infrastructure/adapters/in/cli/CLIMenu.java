package com.techlab.ecommerce.infrastructure.adapters.in.cli;

import com.techlab.ecommerce.application.dto.*;
import com.techlab.ecommerce.application.usecases.pedido.*;
import com.techlab.ecommerce.application.usecases.producto.*;
import org.springframework.stereotype.Component;
import com.techlab.ecommerce.infrastructure.adapters.in.IEntrada;

import java.util.*;
import java.util.List;
import java.util.Scanner;

@Component
public class CLIMenu implements IEntrada {

    private final GestionarProductoUseCase gestionarProductoUseCase;
    private final CrearPedidoUseCase crearPedidoUseCase;
    private final ListarPedidosUseCase listarPedidosUseCase;
    private final ListarProductosUseCase listarProductosUseCase;
    private final ObtenerDetalleProductoUseCase obtenerDetalleProductoUseCase;
    private final Scanner scanner;

    public CLIMenu(GestionarProductoUseCase gestionarProductoUseCase,
                   CrearPedidoUseCase crearPedidoUseCase,
                   ListarPedidosUseCase listarPedidosUseCase,
                   ListarProductosUseCase listarProductosUseCase,
                   ObtenerDetalleProductoUseCase obtenerDetalleProductoUseCase) {
        this.gestionarProductoUseCase = gestionarProductoUseCase;
        this.crearPedidoUseCase = crearPedidoUseCase;
        this.listarPedidosUseCase = listarPedidosUseCase;
        this.listarProductosUseCase = listarProductosUseCase;
        this.obtenerDetalleProductoUseCase = obtenerDetalleProductoUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 7 -> continuar = false;
                default -> System.out.println("Opción inválida");
            }
        }
        scanner.close();
    }

    private void agregarProducto() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            System.out.print("Stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            ProductoDTO producto = new ProductoDTO(null, nombre, precio, stock);
            gestionarProductoUseCase.crearProducto(producto);
            System.out.println("Producto creado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarProductos() {
        try {
            List<ProductoDTO> productos = listarProductosUseCase.ejecutar();
            productos.forEach(p -> System.out.println(
                    p.getId() + " - " + p.getNombre() + " - $" + p.getPrecio() + " - Stock: " + p.getStock()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarProducto() {
        try {
            System.out.print("Nombre producto: ");
            String nombre = scanner.nextLine();
            Optional<ProductoDTO> producto = gestionarProductoUseCase.buscarProductoPorNombre(nombre);
            producto.ifPresentOrElse(
                    p -> System.out.println("Encontrado: " + p.getNombre() + " - $" + p.getPrecio()),
                    () -> System.out.println("No encontrado"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        try {
            System.out.print("ID producto: ");
            UUID id = UUID.fromString(scanner.nextLine());
            gestionarProductoUseCase.eliminarProducto(id);
            System.out.println("Producto eliminado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void crearPedido() {
        try {
            System.out.print("Cantidad de productos: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            Map<UUID, Integer> productos = new HashMap<>();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("ID Producto " + (i+1) + ": ");
                UUID id = UUID.fromString(scanner.nextLine());
                System.out.print("Cantidad: ");
                int cant = scanner.nextInt();
                scanner.nextLine();
                productos.put(id, cant);
            }

            crearPedidoUseCase.ejecutar(productos);
            System.out.println("Pedido creado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarPedidos() {
        try {
            List<PedidoDTO> pedidos = listarPedidosUseCase.ejecutar();
            pedidos.forEach(p -> {
                System.out.println("Pedido ID: " + p.getId());
                p.getLineas().forEach(l ->
                        System.out.println("  - " + l.getNombreProducto() + " x" + l.getCantidad()));
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}