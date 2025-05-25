package com.techlab.ecommerce.adapters.out.repository;

import com.techlab.ecommerce.domain.model.pedido.IPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoRepository {
    void guardar(IPedido pedido);

    Optional<IPedido> buscarPorId(UUID id);

    List<IPedido> obtenerTodos();

    void eliminar(IPedido pedido);
}
