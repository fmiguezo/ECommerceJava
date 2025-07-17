package com.techlab.ecommerce.infrastructure.adapters.in.http;

import com.techlab.ecommerce.application.dto.PedidoDTO;
import com.techlab.ecommerce.application.mapper.PedidoMapper;
import com.techlab.ecommerce.application.usecases.pedido.CrearPedidoUseCase;
import com.techlab.ecommerce.application.usecases.pedido.ListarPedidosUseCase;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class PedidoController {

    private final CrearPedidoUseCase crearPedidoUseCase;
    private final ListarPedidosUseCase listarPedidosUseCase;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Map<UUID, Integer> productos) {
        try {
            IPedido pedidoCreado = crearPedidoUseCase.ejecutar(productos);
            PedidoDTO pedidoDTO = pedidoMapper.toDTO(pedidoCreado);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
        } catch (StockInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Stock insuficiente para el producto: " + e.getMessage());
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado: " + e.getMessage());
        } catch (CantidadNegativaException | LineaPedidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (PedidoException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        List<PedidoDTO> pedidos = listarPedidosUseCase.ejecutar();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPorId(@PathVariable UUID id) {
        try {
            PedidoDTO pedido = listarPedidosUseCase.buscarPorId(id)
                    .orElseThrow(() -> new PedidoNoEncontradoException(id.toString()));
            return ResponseEntity.ok(pedido);
        } catch (PedidoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}