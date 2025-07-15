package com.techlab.ecommerce.domain.model.producto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
public class Producto implements IProducto {
    private UUID id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(UUID id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}