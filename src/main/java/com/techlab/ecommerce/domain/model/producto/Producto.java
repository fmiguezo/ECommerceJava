package com.techlab.ecommerce.domain.model.producto;

import java.util.UUID;

public class Producto implements IProducto {
    private final UUID id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }
}
