package domain;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.Pedido;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoTest {

    @Test
    void testConstructorGeneraIdNoNulo() {
        ILineaPedido lineaMock = mock(ILineaPedido.class);
        Pedido pedido = new Pedido(List.of(lineaMock));

        assertNotNull(pedido.getId(), "El ID del pedido no debería ser nulo");
    }

    @Test
    void testConstructorAsignaLineas() {
        ILineaPedido linea1 = mock(ILineaPedido.class);
        ILineaPedido linea2 = mock(ILineaPedido.class);
        List<ILineaPedido> lineas = List.of(linea1, linea2);

        Pedido pedido = new Pedido(lineas);

        assertEquals(2, pedido.getLineas().size(), "El pedido debería tener dos líneas");
        assertTrue(pedido.getLineas().contains(linea1));
        assertTrue(pedido.getLineas().contains(linea2));
    }

    @Test
    void testGetIdEsInmutable() {
        Pedido pedido = new Pedido(List.of(mock(ILineaPedido.class)));
        UUID idInicial = pedido.getId();

        assertEquals(idInicial, pedido.getId());
    }

    @Test
    void testGetLineasEsInmutableEnReferencia() {
        List<ILineaPedido> lineasOriginales = List.of(mock(ILineaPedido.class));
        Pedido pedido = new Pedido(lineasOriginales);

        List<ILineaPedido> lineasRetornadas = pedido.getLineas();
        assertEquals(lineasOriginales, lineasRetornadas);
    }
}