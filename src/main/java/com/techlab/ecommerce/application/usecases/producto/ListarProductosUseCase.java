package com.techlab.ecommerce.application.usecases.producto;

import com.techlab.ecommerce.application.dto.ProductoDTO;
import com.techlab.ecommerce.application.mapper.ProductoMapper;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListarProductosUseCase {

    private final IProductoService productoService;
    private final ProductoMapper productoMapper;

    @Autowired
    public ListarProductosUseCase(@Lazy IProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> ejecutar() {
        List<IProducto> productos = productoService.findAll();
        return productos.stream()
                .map(productoMapper::toDTO)
                .toList();
    }
}