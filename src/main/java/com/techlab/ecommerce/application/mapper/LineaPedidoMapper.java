package com.techlab.ecommerce.application.mapper;

import com.techlab.ecommerce.application.dto.LineaPedidoDTO;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LineaPedidoMapper {
    @Autowired
    private ProductoService productoService;

    public LineaPedidoDTO toDTO(ILineaPedido linea) {
        if (linea == null || linea.getProducto() == null) return null;

        return new LineaPedidoDTO(
                linea.getProducto().getNombre(),
                linea.getCantidad(),
                linea.getProducto().getPrecio()
        );
    }

    public LineaPedido toDomain(Optional<LineaPedidoDTO> dtoOptional) {
        if (dtoOptional == null || !dtoOptional.isPresent()) {
            return null;
        }

        LineaPedidoDTO dto = dtoOptional.get();

        IProducto producto = productoService.findByNombre(dto.getNombreProducto())
                .orElse(null);

        return new LineaPedido(producto, dto.getCantidad());
    }
}