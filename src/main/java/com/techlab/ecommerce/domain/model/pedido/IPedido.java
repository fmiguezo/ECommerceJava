package com.techlab.ecommerce.domain.model.pedido;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;

import java.util.List;
import java.util.UUID;

public interface IPedido {
    UUID getId();
    List<ILineaPedido> getLineas();
}
