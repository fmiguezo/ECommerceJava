package domain;

import com.techlab.ecommerce.domain.exceptions.CantidadNegativaException;
import com.techlab.ecommerce.domain.model.lineapedido.LineaPedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LineaPedidoTest {

    private IProducto productoMock;
    private LineaPedido lineaPedido;

    @BeforeEach
    void setUp() {
        productoMock = Mockito.mock(IProducto.class);
        lineaPedido = new LineaPedido(productoMock, 3);
    }

    @Test
    void testGetId_NotNull() {
        assertNotNull(lineaPedido.getId(), "El ID no debería ser nulo");
    }

    @Test
    void testGetProducto() {
        assertEquals(productoMock, lineaPedido.getProducto(), "Debería devolver el producto asignado");
    }

    @Test
    void testGetCantidad() {
        assertEquals(3, lineaPedido.getCantidad(), "Debería devolver la cantidad correcta");
    }

    @Test
    void testSetCantidad_ValorValido() {
        assertDoesNotThrow(() -> lineaPedido.setCantidad(5));
        assertEquals(5, lineaPedido.getCantidad(), "La cantidad debería actualizarse correctamente");
    }

    @Test
    void testSetCantidad_CantidadNegativa_LanzaExcepcion() {
        assertThrows(CantidadNegativaException.class, () -> lineaPedido.setCantidad(-1),
                "Debería lanzar CantidadNegativaException si se intenta establecer una cantidad negativa");
    }

    @Test
    void testIdsSonUnicos() {
        LineaPedido otraLinea = new LineaPedido(productoMock, 1);
        assertNotEquals(lineaPedido.getId(), otraLinea.getId(), "Cada línea de pedido debería tener un ID único");
    }
}