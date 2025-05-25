package com.techlab.ecommerce.adapters.in;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.application.dto.PedidoDTO;
import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.application.usecase.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CLIMenu implements IEntrada {
    private final GestionarProductoUseCase gestionarProductoUseCase;
    private final CrearPedidoUseCase crearPedidoUseCase;
    private final ListarPedidosUseCase listarPedidosUseCase;
    private final ListarProductosUseCase listarProductosUseCase;
    private final ObtenerDetalleProductoUseCase obtenerDetalleProductoUseCase;
    private final Scanner scanner;

    public CLIMenu(GestionarProductoUseCase gestionarProductoUseCase, CrearPedidoUseCase crearPedidoUseCase, ListarPedidosUseCase listarPedidosUseCase, ListarProductosUseCase listarProductosUseCase, ObtenerDetalleProductoUseCase obtenerDetalleProductoUseCase) {
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
            System.out.println("=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarActualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    crearPedido();
                    break;
                case 6:
                    listarPedidos();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }

    private void agregarProducto() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese stock del producto: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        try {
            gestionarProductoUseCase.crearProducto(nombre, precio, stock);
            System.out.println("Producto agregado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    private void listarProductos() {
        List<ProductoDTO> productos = listarProductosUseCase.ejecutar();
        System.out.println("Listado de productos:");
        for (ProductoDTO producto : productos) {
            System.out.println("ID: " + producto.getId() + " | Nombre: " + producto.getNombre() +
                    " | Precio: " + producto.getPrecio() + " | Stock: " + producto.getStock());
        }
    }

    private void buscarActualizarProducto() {
        System.out.print("Ingrese nombre del producto a buscar: ");
        String nombre = scanner.nextLine();
        try {
            Optional<IProducto> producto = gestionarProductoUseCase.buscarProducto(nombre);
            if (producto.isPresent()) {
                ProductoDTO productoDTO = obtenerDetalleProductoUseCase.ejecutar(producto.get().getId());
                System.out.println("Producto encontrado: " + productoDTO.getNombre() +
                        " | Precio: " + productoDTO.getPrecio() + " | Stock: " + productoDTO.getStock());
            } else {
                System.out.println("Producto no encontrado.");
            }
            System.out.print("¿Desea actualizar el producto? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese nuevo precio: ");
                double nuevoPrecio = scanner.nextDouble();
                System.out.print("Ingrese nuevo stock: ");
                int nuevoStock = scanner.nextInt();
                scanner.nextLine();
                gestionarProductoUseCase.actualizarProducto(nombre, nuevoPrecio, nuevoStock);
                System.out.println("Producto actualizado con éxito.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        System.out.print("Ingrese nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
        try {
            Optional<IProducto> producto = gestionarProductoUseCase.buscarProducto(nombre);
            if (producto.isPresent()) {
                gestionarProductoUseCase.eliminarProducto(producto.get().getId());
                System.out.println("Producto eliminado con éxito.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void crearPedido() {
        System.out.print("¿Cuántos productos desea agregar al pedido? ");
        int cantidadProductos = scanner.nextInt();
        scanner.nextLine();

        java.util.Map<UUID, Integer> productosSolicitados = new java.util.HashMap<>();

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.print("Ingrese el nombre del producto: ");
            String nombreProducto = scanner.nextLine();
            Optional<IProducto> producto;
            try {
               producto = gestionarProductoUseCase.buscarProducto(nombreProducto);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                i--;
                continue;
            }
            System.out.print("Ingrese cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            productosSolicitados.put(producto.get().getId(), cantidad);
        }

        try {
            crearPedidoUseCase.crearPedido(productosSolicitados);
            System.out.println("Pedido creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
        }
    }

    private void listarPedidos() {
        List<PedidoDTO> pedidos = listarPedidosUseCase.ejecutar();
        System.out.println("Listado de pedidos:");
        for (PedidoDTO pedido : pedidos) {
            System.out.println("ID Pedido: " + pedido.getId() + " | Costo Total: " + pedido.getCostoTotal());
            System.out.println("Productos:");
            for (LineaPedidoDTO linea : pedido.getLineas()) {
                System.out.println("- " + linea.getNombreProducto() + " x " + linea.getCantidad());
            }
        }
    }

}
