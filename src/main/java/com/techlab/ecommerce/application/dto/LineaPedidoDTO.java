package com.techlab.ecommerce.application.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaPedidoDTO {
    @NotBlank
    private String nombreProducto;

    @Positive
    private int cantidad;

    @Positive
    private double precioUnitario;
}