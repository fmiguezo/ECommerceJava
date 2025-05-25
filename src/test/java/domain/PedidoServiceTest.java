package domain;

import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.service.lineapedido.ILineaPedidoService;
import com.techlab.ecommerce.domain.service.pedido.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    private ILineaPedidoService lineaPedidoService;
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        lineaPedidoService = mock(ILineaPedidoService.class);
        pedidoService = new PedidoService(lineaPedidoService);
    }

    @Test
    void testCalcularTotalPedido() {
        ILineaPedido linea1 = mock(ILineaPedido.class);
        ILineaPedido linea2 = mock(ILineaPedido.class);

        IPedido pedido = mock(IPedido.class);
        when(pedido.getLineas()).thenReturn(List.of(linea1, linea2));
        when(lineaPedidoService.calcularTotal(linea1)).thenReturn(100.0);
        when(lineaPedidoService.calcularTotal(linea2)).thenReturn(150.0);

        double total = pedidoService.calcularTotal(pedido);

        assertEquals(250.0, total, 0.001);
        verify(lineaPedidoService).calcularTotal(linea1);
        verify(lineaPedidoService).calcularTotal(linea2);
    }

    @Test
    void testProcesarPedidoSinErrores() throws Exception {
        ILineaPedido linea1 = mock(ILineaPedido.class);
        ILineaPedido linea2 = mock(ILineaPedido.class);

        IPedido pedido = mock(IPedido.class);
        when(pedido.getLineas()).thenReturn(List.of(linea1, linea2));

        // No lanzan excepción
        doNothing().when(lineaPedidoService).validarStock(any());
        doNothing().when(lineaPedidoService).decrementarCantidad(any());

        pedidoService.procesarPedido(pedido);

        verify(lineaPedidoService).validarStock(linea1);
        verify(lineaPedidoService).validarStock(linea2);
        verify(lineaPedidoService).decrementarCantidad(linea1);
        verify(lineaPedidoService).decrementarCantidad(linea2);
    }

    @Test
    void testProcesarPedidoConExcepcionEnUnaLinea() throws Exception {
        ILineaPedido linea1 = mock(ILineaPedido.class);
        ILineaPedido linea2 = mock(ILineaPedido.class);

        IPedido pedido = mock(IPedido.class);
        when(pedido.getLineas()).thenReturn(List.of(linea1, linea2));

        doThrow(new RuntimeException("Stock insuficiente")).when(lineaPedidoService).validarStock(linea1);
        doNothing().when(lineaPedidoService).validarStock(linea2);
        doNothing().when(lineaPedidoService).decrementarCantidad(linea2);

        pedidoService.procesarPedido(pedido);

        verify(lineaPedidoService).validarStock(linea1);
        verify(lineaPedidoService).validarStock(linea2);
        verify(lineaPedidoService, never()).decrementarCantidad(linea1);
        verify(lineaPedidoService).decrementarCantidad(linea2);
    }

    @Test
    void testSetLineaPedidoService() {
        ILineaPedidoService nuevoServicio = mock(ILineaPedidoService.class);
        pedidoService.setLineaPedidoService(nuevoServicio);
    }
}