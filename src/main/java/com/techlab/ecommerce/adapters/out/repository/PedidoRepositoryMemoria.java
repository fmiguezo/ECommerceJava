package com.techlab.ecommerce.adapters.out.repository;

import com.techlab.ecommerce.domain.model.pedido.IPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PedidoRepositoryMemoria implements IPedidoRepository {
    private final List<IPedido> pedidos = new ArrayList<>();

    @Override
    public void guardar(IPedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }
        if (pedidos.stream().anyMatch(p -> p.getId().equals(pedido.getId()))) {
            throw new IllegalArgumentException("El pedido ya existe");
        }
        pedidos.add(pedido);
    }

    @Override
    public Optional<IPedido> buscarPorId(UUID id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<IPedido> obtenerTodos() {
        return pedidos;
    }

    @Override
    public void eliminar(IPedido pedido) {
        pedidos.remove(pedido);
    }
}
