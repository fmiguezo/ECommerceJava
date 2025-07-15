package com.techlab.ecommerce.infrastructure.adapters.in.http;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:63342")
public class PedidoController {

}
