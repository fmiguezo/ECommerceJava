package com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria;

import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.infrastructure.ports.out.IPedidoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Profile("memory")
@Component
public class RepositoryMemoriaPedido implements IPedidoRepository {

    private final Map<UUID, IPedido> pedidos = new ConcurrentHashMap<>();

    @Override
    public IPedido save(IPedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no puede ser nulo");
        }
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    @Override
    public Optional<IPedido> findById(UUID id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    @Override
    public List<IPedido> findAll() {
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public void deleteById(UUID id) {
        pedidos.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return pedidos.containsKey(id);
    }
}