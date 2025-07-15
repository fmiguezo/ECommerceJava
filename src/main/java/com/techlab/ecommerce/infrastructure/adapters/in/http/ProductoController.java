package com.techlab.ecommerce.infrastructure.adapters.in.http;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.application.usecases.producto.GestionarProductoUseCase;
import com.techlab.ecommerce.domain.exceptions.ProductoYaExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:63342")
public class ProductoController {
    private final GestionarProductoUseCase gestionarProductoUseCase;

    @Autowired
    public ProductoController(GestionarProductoUseCase gestionarProductoUseCase) {
        this.gestionarProductoUseCase = gestionarProductoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO>  crearProducto(@RequestBody ProductoDTO productoDTO) throws ProductoYaExistenteException {
        ProductoDTO productoCreado = gestionarProductoUseCase.crearProducto(productoDTO.getNombre(), productoDTO.getPrecio(), productoDTO.getStock());
        return ResponseEntity.ok(productoCreado);
    }

}
