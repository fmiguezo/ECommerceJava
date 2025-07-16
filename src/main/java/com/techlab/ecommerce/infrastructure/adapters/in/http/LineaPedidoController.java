package com.techlab.ecommerce.infrastructure.adapters.in.http;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.application.usecases.lineapedido.GestionarLineaPedidoUseCase;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/lineas-pedido")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class LineaPedidoController {

    private final GestionarLineaPedidoUseCase gestionarLineaPedidoUseCase;
    private final ILineaPedidoService lineaPedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<LineaPedidoDTO> obtenerLinea(@PathVariable UUID id) {
        try {
            LineaPedidoDTO lineaPedido = gestionarLineaPedidoUseCase.buscarLineaPorId(id)
                    .orElseThrow(() -> new LineaPedidoNoEncontradaException("Linea de pedido no encontrada con ID: " + id));
            return ResponseEntity.ok(lineaPedido);
        } catch (LineaPedidoNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/cantidad")
    public ResponseEntity<?> ajustarCantidad(
            @PathVariable UUID id,
            @RequestParam int cantidad) {
        try {
            Optional<LineaPedidoDTO> linea = gestionarLineaPedidoUseCase.buscarLineaPorId(id);
            Optional<LineaPedidoDTO> lineaActualizada = gestionarLineaPedidoUseCase.ajustarCantidad(linea, cantidad);

            return ResponseEntity.ok(lineaActualizada);
        } catch (LineaPedidoNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (LineaPedidoInvalidaException | StockInsuficienteException |
                 CantidadNegativaException | ProductoException e) {
            return ResponseEntity.badRequest().body(
                    ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), e.getMessage()));
        } catch (ProductoNoEncontradoException e) {
            throw new RuntimeException(e);
        } catch (LineaPedidoException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLinea(@PathVariable UUID id) {
        try {
            gestionarLineaPedidoUseCase.eliminarLinea(id);
            return ResponseEntity.noContent().build();
        } catch (LineaPedidoNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}