package com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private List<LineaPedidoEntity> lineas = new ArrayList<>();

    public PedidoEntity() {
    }

    public PedidoEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Transactional
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<LineaPedidoEntity> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoEntity> lineas) {
        this.lineas = lineas;
    }
}
