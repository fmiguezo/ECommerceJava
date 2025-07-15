package com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.infrastructure.ports.out.ILineaPedidoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Profile("memory")
@Component
public class RepositoryMemoriaLineaPedido implements ILineaPedidoRepository {

    private final Map<UUID, ILineaPedido> lineas = new ConcurrentHashMap<>();
    private final Map<UUID, List<UUID>> pedidoLineas = new ConcurrentHashMap<>();

    @Override
    public ILineaPedido save(ILineaPedido lineaPedido) {
        if (lineaPedido == null) {
            throw new IllegalArgumentException("Línea de pedido no puede ser nula");
        }

        lineas.put(lineaPedido.getId(), lineaPedido);
        return lineaPedido;
    }

    @Override
    public void deleteByPedidoId(UUID pedidoId) {
        lineas.values().removeIf(linea -> {
            return true;
        });
    }

    public void asociarAPedido(UUID lineaPedidoId, UUID pedidoId) {
        lineas.computeIfPresent(lineaPedidoId, (id, linea) -> {
            pedidoLineas.computeIfAbsent(pedidoId, k -> new ArrayList<>()).add(id);
            return linea;
        });
    }
}