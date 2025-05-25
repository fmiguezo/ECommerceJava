package domain;

import com.techlab.ecommerce.adapters.out.repository.IProductoRepository;
import com.techlab.ecommerce.domain.exceptions.*;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.model.producto.Producto;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    private IProductoRepository productoRepository;
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoRepository = mock(IProductoRepository.class);
        productoService = new ProductoService(productoRepository);
    }

    @Test
    void crearProducto_DeberiaCrearProductoSiNoExiste() throws ProductoYaExistenteException {
        when(productoRepository.buscar("Laptop")).thenReturn(null);

        IProducto producto = productoService.crearProducto("Laptop", 1000.0, 10);

        assertEquals("Laptop", producto.getNombre());
        assertEquals(1000.0, producto.getPrecio());
        assertEquals(10, producto.getStock());

        verify(productoRepository).crear(any(IProducto.class));
    }

    @Test
    void crearProducto_DeberiaLanzarExcepcionSiYaExiste() throws ProductoYaExistenteException {
        IProducto productoExistente = new Producto("Laptop", 1000.0, 10);
        when(productoRepository.buscar("Laptop")).thenReturn(productoExistente);

        assertThrows(ProductoYaExistenteException.class, () -> {
            productoService.crearProducto("Laptop", 1000.0, 10);
        });

        verify(productoRepository, never()).crear(any());
    }

    @Test
    void listarProductos_DeberiaDevolverListaDeProductos() {
        List<IProducto> productos = Arrays.asList(
                new Producto("Laptop", 1000.0, 10),
                new Producto("Mouse", 20.0, 50)
        );
        when(productoRepository.obtenerTodos()).thenReturn(productos);

        List<IProducto> resultado = productoService.listarProductos();

        assertEquals(2, resultado.size());
    }

    @Test
    void buscarProductoPorNombre_DeberiaDevolverProductoSiExiste() {
        IProducto producto = new Producto("Teclado", 50.0, 15);
        when(productoRepository.buscar("Teclado")).thenReturn(producto);

        Optional<IProducto> resultado = productoService.buscarProducto("Teclado");

        assertTrue(resultado.isPresent());
        assertEquals("Teclado", resultado.get().getNombre());
    }

    @Test
    void actualizarProducto_DeberiaActualizarSiExiste() throws Exception {
        IProducto producto = new Producto("Tablet", 500.0, 5);
        when(productoRepository.buscar("Tablet")).thenReturn(producto);

        productoService.actualizarProducto("Tablet", 600.0, 8);

        assertEquals(600.0, producto.getPrecio());
        assertEquals(8, producto.getStock());
        verify(productoRepository).actualizar(producto);
    }

    @Test
    void eliminarProducto_DeberiaEliminarSiExiste() throws Exception {
        IProducto producto = new Producto("Monitor", 300.0, 7);

        when(productoRepository.buscar(producto.getId())).thenReturn(producto);

        productoService.eliminarProducto(producto.getId());

        verify(productoRepository).eliminar(producto.getId());
    }

    @Test
    void disminuirStock_DeberiaReducirStockSiEsSuficiente() throws Exception {
        IProducto producto = new Producto("Silla", 100.0, 10);
        productoService.disminuirStock(producto, 3);
        assertEquals(7, producto.getStock());
        verify(productoRepository).actualizar(producto);
    }

    @Test
    void disminuirStock_DeberiaLanzarExcepcionSiNoHayStock() {
        IProducto producto = new Producto("Silla", 100.0, 2);
        assertThrows(StockInsuficienteException.class, () -> {
            productoService.disminuirStock(producto, 3);
        });
        verify(productoRepository, never()).actualizar(any());
    }

    @Test
    void aumentarStock_DeberiaSumarCantidadSiEsValida() throws Exception {
        IProducto producto = new Producto("Mesa", 200.0, 5);
        productoService.aumentarStock(producto, 5);
        assertEquals(10, producto.getStock());
        verify(productoRepository).actualizar(producto);
    }

    @Test
    void aumentarStock_DeberiaLanzarExcepcionSiCantidadEsNegativa() {
        IProducto producto = new Producto("Mesa", 200.0, 5);
        assertThrows(CantidadNegativaException.class, () -> {
            productoService.aumentarStock(producto, -3);
        });
        verify(productoRepository, never()).actualizar(any());
    }
}