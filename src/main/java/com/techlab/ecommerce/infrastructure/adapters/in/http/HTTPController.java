package com.techlab.ecommerce.infrastructure.adapters.in.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HTTPController {

        @GetMapping("/")
        public String apiHome() {
            return "Bienvenido a la API!";
        }
}
