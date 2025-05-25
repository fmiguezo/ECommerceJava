package com.techlab.ecommerce.domain.model.producto;

import java.util.UUID;

public interface IProducto {
    UUID getId();
    String getNombre();
    void setNombre(String nombre);
    double getPrecio();
    void setPrecio(double precio);
    int getStock();
    void setStock(int stock);
}
