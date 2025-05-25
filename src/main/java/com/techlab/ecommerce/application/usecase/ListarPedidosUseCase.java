package com.techlab.ecommerce.application.usecase;

import com.techlab.ecommerce.application.dto.PedidoDTO;
import com.techlab.ecommerce.application.mapper.PedidoMapper;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.infrastructure.ports.out.IPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPedidosUseCase {

    private final IPedidoRepository pedidoRepository;

    public ListarPedidosUseCase(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoDTO> ejecutar() {
        List<IPedido> pedidos = pedidoRepository.obtenerTodos();
        return pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
    }
}