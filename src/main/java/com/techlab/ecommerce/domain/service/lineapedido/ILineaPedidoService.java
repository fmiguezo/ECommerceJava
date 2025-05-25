package com.techlab.ecommerce.domain.service.lineapedido;

import com.techlab.ecommerce.domain.exceptions.CantidadNegativaException;
import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.exceptions.StockInsuficienteException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;

public interface ILineaPedidoService {
    double calcularTotal(ILineaPedido lineaPedido);
    void incrementarCantidad(ILineaPedido lineaPedido) throws StockInsuficienteException, CantidadNegativaException;
    void decrementarCantidad(ILineaPedido lineaPedido) throws CantidadNegativaException;
    void validarStock(ILineaPedido lineaPedido) throws StockInsuficienteException, ProductoNoEncontradoException;
}
