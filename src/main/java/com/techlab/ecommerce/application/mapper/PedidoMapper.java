package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.application.dto.PedidoDTO;
import com.techlab.ecommerce.domain.model.pedido.IPedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(IPedido pedido) {
        List<LineaPedidoDTO> lineasDTO = pedido.getLineas().stream()
                .map(linea -> new LineaPedidoDTO(
                        linea.getProducto().getNombre(),
                        linea.getCantidad(),
                        linea.getProducto().getPrecio()))
                .collect(Collectors.toList());

        double costoTotal = lineasDTO.stream()
                .mapToDouble(l -> l.getCantidad() * l.getPrecioUnitario())
                .sum();

        return new PedidoDTO(pedido.getId(), lineasDTO, costoTotal);
    }
}
