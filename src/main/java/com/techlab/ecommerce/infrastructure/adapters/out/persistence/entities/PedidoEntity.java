package com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaPedidoEntity> lineas = new ArrayList<>();

    // Constructor personalizado (sin @AllArgsConstructor)
    public PedidoEntity(UUID id) {
        this.id = id;
    }
}