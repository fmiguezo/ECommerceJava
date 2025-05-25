package com.techlab.ecommerce.application.dto;

import java.util.List;
import java.util.UUID;

public class PedidoDTO {
    private UUID id;
    private List<LineaPedidoDTO> lineas;
    private double costoTotal;

    public PedidoDTO(UUID id, List<LineaPedidoDTO> lineas, double costoTotal) {
        this.id = id;
        this.lineas = lineas;
        this.costoTotal = costoTotal;
    }

    public UUID getId() {
        return id;
    }

    public List<LineaPedidoDTO> getLineas() {
        return lineas;
    }

    public double getCostoTotal() {
        return costoTotal;
    }
}