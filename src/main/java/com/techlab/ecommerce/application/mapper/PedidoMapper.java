package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.*;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final LineaPedidoMapper lineaPedidoMapper;

    public PedidoDTO toDTO(IPedido pedido) {
        if (pedido == null) return null;

        List<LineaPedidoDTO> lineasDTO = pedido.getLineas().stream()
                .map(lineaPedidoMapper::toDTO)
                .collect(Collectors.toList());

        double costoTotal = lineasDTO.stream()
                .mapToDouble(l -> l.getCantidad() * l.getPrecioUnitario())
                .sum();

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setLineas(lineasDTO);
        dto.setCostoTotal(costoTotal);
        return dto;
    }
}