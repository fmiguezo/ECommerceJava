package com.techlab.ecommerce.application.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private UUID id;
    private List<LineaPedidoDTO> lineas;

    @Positive
    private double costoTotal;
}