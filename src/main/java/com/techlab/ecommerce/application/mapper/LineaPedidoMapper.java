package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LineaPedidoMapper {

    public LineaPedidoDTO toDTO(ILineaPedido linea) {
        if (linea == null || linea.getProducto() == null) return null;

        return new LineaPedidoDTO(
                linea.getProducto().getNombre(),
                linea.getCantidad(),
                linea.getProducto().getPrecio()
        );
    }

    public ILineaPedido toDomain(Optional<LineaPedidoDTO> dtoOptional, IProducto producto) {
        if (dtoOptional == null || !dtoOptional.isPresent()) {
            return null;
        }

        LineaPedidoDTO dto = dtoOptional.get();

        return new LineaPedido(producto, dto.getCantidad());
    }
}
