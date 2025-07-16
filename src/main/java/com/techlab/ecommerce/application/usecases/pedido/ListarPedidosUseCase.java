package com.techlab.ecommerce.application.usecases.pedido;

import com.techlab.ecommerce.application.dto.PedidoDTO;
import com.techlab.ecommerce.application.mapper.PedidoMapper;
import com.techlab.ecommerce.domain.service.pedido.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarPedidosUseCase {

    private final IPedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public List<PedidoDTO> ejecutar() {
        return pedidoService.findAll()
                .stream()
                .map(pedidoMapper::toDTO)
                .toList();
    }

    public Optional<PedidoDTO> buscarPorId(UUID id) {
        return pedidoService.findById(id)
                .map(pedidoMapper::toDTO);
    }
}