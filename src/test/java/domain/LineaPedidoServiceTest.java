package domain;

import com.techlab.ecommerce.domain.exceptions.CantidadNegativaException;
import com.techlab.ecommerce.domain.exceptions.ProductoNoEncontradoException;
import com.techlab.ecommerce.domain.exceptions.StockInsuficienteException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.lineapedido.LineaPedidoService;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LineaPedidoServiceTest {

    private ProductoService productoServiceMock;
    private LineaPedidoService lineaPedidoService;
    private ILineaPedido lineaPedidoMock;
    private IProducto productoMock;

    @BeforeEach
    void setUp() {
        productoServiceMock = mock(ProductoService.class);
        lineaPedidoService = new LineaPedidoService(productoServiceMock);
        lineaPedidoMock = mock(ILineaPedido.class);
        productoMock = mock(IProducto.class);

        when(lineaPedidoMock.getProducto()).thenReturn(productoMock);
    }

    @Test
    void testCalcularTotal() {
        when(lineaPedidoMock.getCantidad()).thenReturn(2);
        when(productoMock.getPrecio()).thenReturn(50.0);

        double total = lineaPedidoService.calcularTotal(lineaPedidoMock);
        assertEquals(100.0, total, 0.01);
    }

    @Test
    void testIncrementarCantidad() throws CantidadNegativaException {
        when(lineaPedidoMock.getCantidad()).thenReturn(2);

        lineaPedidoService.incrementarCantidad(lineaPedidoMock);

        verify(lineaPedidoMock).setCantidad(3);
    }

    @Test
    void testDecrementarCantidad() throws CantidadNegativaException {
        when(lineaPedidoMock.getCantidad()).thenReturn(2);

        lineaPedidoService.decrementarCantidad(lineaPedidoMock);

        verify(lineaPedidoMock).setCantidad(1);
    }

    @Test
    void testDecrementarCantidad_LanzaExcepcion() {
        when(lineaPedidoMock.getCantidad()).thenReturn(0);

        assertThrows(CantidadNegativaException.class,
                () -> lineaPedidoService.decrementarCantidad(lineaPedidoMock));
    }

    @Test
    void testValidarStock_StockSuficiente() {
        when(lineaPedidoMock.getCantidad()).thenReturn(3);
        when(productoMock.getStock()).thenReturn(5);

        assertDoesNotThrow(() -> lineaPedidoService.validarStock(lineaPedidoMock));
    }

    @Test
    void testValidarStock_ProductoNull() {
        when(lineaPedidoMock.getProducto()).thenReturn(null);

        assertThrows(ProductoNoEncontradoException.class,
                () -> lineaPedidoService.validarStock(lineaPedidoMock));
    }

    @Test
    void testValidarStock_StockInsuficiente() {
        when(lineaPedidoMock.getCantidad()).thenReturn(10);
        when(productoMock.getStock()).thenReturn(5);
        when(productoMock.getNombre()).thenReturn("ProductoTest");

        assertThrows(StockInsuficienteException.class,
                () -> lineaPedidoService.validarStock(lineaPedidoMock));
    }
}