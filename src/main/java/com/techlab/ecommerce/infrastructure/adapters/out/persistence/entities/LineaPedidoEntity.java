package com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "linea_pedido")
public class LineaPedidoEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private int cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntity producto;

    public LineaPedidoEntity() {
    }

    public LineaPedidoEntity(UUID id, ProductoEntity producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
