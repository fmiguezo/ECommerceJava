package com.techlab.ecommerce.domain.service.lineapedido;

import com.techlab.ecommerce.domain.exceptions.CantidadNegativaException;
import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.exceptions.StockInsuficienteException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.service.producto.ProductoService;

public class LineaPedidoService implements ILineaPedidoService {
    private ProductoService productoService;

    public LineaPedidoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public double calcularTotal(ILineaPedido lineaPedido) {
        return lineaPedido.getCantidad() * lineaPedido.getProducto().getPrecio();
    }

    @Override
    public void incrementarCantidad(ILineaPedido lineaPedido) throws CantidadNegativaException {
        lineaPedido.setCantidad(lineaPedido.getCantidad() + 1);
    }

    @Override
    public void decrementarCantidad(ILineaPedido lineaPedido) throws CantidadNegativaException {
        if (lineaPedido.getCantidad() - 1 < 0) {
            throw new CantidadNegativaException("La cantidad no puede ser negativa");
        }
        lineaPedido.setCantidad(lineaPedido.getCantidad() - 1);
    }

    @Override
    public void validarStock(ILineaPedido lineaPedido) throws StockInsuficienteException, ProductoNoEncontradoException {
        if (lineaPedido.getProducto() == null) {
            throw new ProductoNoEncontradoException("El producto no existe");
        }
        if (lineaPedido.getCantidad() > lineaPedido.getProducto().getStock()) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + lineaPedido.getProducto().getNombre());
        }
    }
}
