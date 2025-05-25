package domain;

import com.techlab.ecommerce.domain.model.producto.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class ProductoTest {
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Café Premium", 1500.50, 20);
    }

    @Test
    void testConstructorInicializaCamposCorrectamente() {
        assertNotNull(producto.getId());
        assertEquals("Café Premium", producto.getNombre());
        assertEquals(1500.50, producto.getPrecio());
        assertEquals(20, producto.getStock());
    }

    @Test
    void testSetNombre() {
        producto.setNombre("Té Verde");
        assertEquals("Té Verde", producto.getNombre());
    }

    @Test
    void testSetPrecio() {
        producto.setPrecio(999.99);
        assertEquals(999.99, producto.getPrecio());
    }

    @Test
    void testSetStock() {
        producto.setStock(50);
        assertEquals(50, producto.getStock());
    }

    @Test
    void testIdEsInmutableYUnico() {
        UUID id1 = producto.getId();
        Producto otroProducto = new Producto("Otro", 100.0, 1);
        UUID id2 = otroProducto.getId();

        assertNotEquals(id1, id2);
        assertEquals(id1, producto.getId()); // sigue siendo el mismo
    }
}
