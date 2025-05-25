package com.techlab.ecommerce.application.dto;

public class LineaPedidoDTO {
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;

    public LineaPedidoDTO(String nombreProducto, int cantidad, double precioUnitario) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}
