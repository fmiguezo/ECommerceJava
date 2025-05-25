package com.techlab.ecommerce.domain.model.producto;

import java.util.UUID;

public interface IProducto {
    UUID getId();
    void setId(UUID id);
    String getNombre();
    void setNombre(String nombre);
    double getPrecio();
    void setPrecio(double precio);
    int getStock();
    void setStock(int stock);
}
