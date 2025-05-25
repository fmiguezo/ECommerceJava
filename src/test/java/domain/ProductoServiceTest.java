package domain;

import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.ProductoFactory;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;


public class ProductoServiceTest {
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoService = new ProductoService();
    }

    @Test
    void crearProducto_DeberiaCrearProductoNuevo() throws ProductoYaExistenteException {
        IProducto producto = productoService.crearProducto("Café", 1000, 10);
        assertEquals("Café", producto.getNombre());
        assertEquals(1000, producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    public void crearProducto_DebeLanzarExcepcion_SiNombreRepetido() throws ProductoYaExistenteException {
        productoService.crearProducto("Pera", 15.0, 20);

        ProductoYaExistenteException exception = assertThrows(
                ProductoYaExistenteException.class,
                () -> productoService.crearProducto("Pera", 20.0, 10)
        );

        assertEquals("El producto ya existe con el nombre: Pera", exception.getMessage());
    }

    @Test
    void buscarProductoPorNombre_Existente() throws ProductoYaExistenteException {
        productoService.crearProducto("Té", 500, 15);
        Optional<IProducto> encontrado = productoService.buscarProducto("Té");

        assertTrue(encontrado.isPresent());
        assertEquals("Té", encontrado.get().getNombre());
    }

    @Test
    void aumentarStock_DeberiaAumentar() throws CantidadNegativaException, ProductoYaExistenteException {
        IProducto producto = productoService.crearProducto("Café", 1000, 10);
        productoService.aumentarStock(producto, 5);

        assertEquals(15, producto.getStock());
    }

    @Test
    void aumentarStock_DeberiaLanzarCantidadNegativaException() throws ProductoYaExistenteException {
        IProducto producto = productoService.crearProducto("Café", 1000, 10);

        assertThrows(CantidadNegativaException.class, () ->
                productoService.aumentarStock(producto, -3));
    }

    @Test
    void disminuirStock_DeberiaReducirStock() throws StockInsuficienteException, ProductoYaExistenteException {
        IProducto producto = productoService.crearProducto("Té", 800, 10);
        productoService.disminuirStock(producto, 4);

        assertEquals(6, producto.getStock());
    }

    @Test
    void disminuirStock_DeberiaLanzarStockInsuficienteException() throws ProductoYaExistenteException {
        IProducto producto = productoService.crearProducto("Té", 800, 5);

        assertThrows(StockInsuficienteException.class, () ->
                productoService.disminuirStock(producto, 10));
    }

    @Test
    void getProductoFactory_SinFactory_DeberiaLanzarExcepcion() {
        assertThrows(ProductFactoryNotSetException.class, () -> {
            productoService.getProductoFactory();
        });
    }

    @Test
    void getProductoFactory_ConFactory_DeberiaRetornarFactory() throws ProductFactoryNotSetException {
        ProductoFactory factoryMock = mock(ProductoFactory.class);
        productoService.setProductoFactory(factoryMock);

        assertEquals(factoryMock, productoService.getProductoFactory());
    }

    @Test
    void actualizarProducto_NoExistente_DeberiaLanzarExcepcion() {
        assertThrows(ProductoNoEncontradoException.class, () ->
                productoService.actualizarProducto(999, 100, 10));
    }

    @Test
    void eliminarProducto_NoExistente_DeberiaLanzarExcepcion() {
        assertThrows(ProductoNoEncontradoException.class, () ->
                productoService.eliminarProducto(999));
    }

}
