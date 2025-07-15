package com.techlab.ecommerce.application.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private UUID id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Positive(message = "El precio debe ser mayor a 0")
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;
}