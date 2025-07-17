package com.techlab.ecommerce.infrastructure.adapters.in.http;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.application.usecases.producto.GestionarProductoUseCase;
import com.techlab.ecommerce.domain.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class ProductoController {

    private final GestionarProductoUseCase gestionarProductoUseCase;

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            ProductoDTO productoCreado = gestionarProductoUseCase.crearProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
        } catch (ProductoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = gestionarProductoUseCase.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable UUID id) {
        Optional<ProductoDTO> producto = gestionarProductoUseCase.buscarProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<ProductoDTO> obtenerProductoPorNombre(@RequestParam String nombre) {
        Optional<ProductoDTO> producto = gestionarProductoUseCase.buscarProductoPorNombre(nombre);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable UUID id,
            @RequestBody ProductoDTO productoDTO) {
        try {
            ProductoDTO productoActualizado = gestionarProductoUseCase.actualizarProducto(id, productoDTO);
            return ResponseEntity.ok(productoActualizado);
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (ProductoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable UUID id) {
        try {
            gestionarProductoUseCase.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoDTO> ajustarStock(
            @PathVariable UUID id,
            @RequestParam int cantidad) {
        try {
            ProductoDTO productoActualizado = gestionarProductoUseCase.ajustarStock(id, cantidad);
            return ResponseEntity.ok(productoActualizado);
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (StockInsuficienteException e) {
            return ResponseEntity.badRequest().build();
        } catch (ProductoException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        try {
            return ResponseEntity.ok("Conexión exitosa a controlador");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}