package application;

import com.techlab.ecommerce.adapters.out.repository.IPedidoRepository;
import com.techlab.ecommerce.adapters.out.repository.IProductoRepository;
import com.techlab.ecommerce.application.usecase.CrearPedidoUseCase;
import com.techlab.ecommerce.domain.exceptions.StockInsuficienteException;
import com.techlab.ecommerce.domain.model.lineapedido.ILineaPedido;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrearPedidoUseCaseTest {

    @Mock
    IProductoRepository productoRepository;

    @Mock
    IPedidoRepository pedidoRepository;

    @Mock
    IProducto productoMock;

    @InjectMocks
    CrearPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new CrearPedidoUseCase(productoRepository, pedidoRepository);
    }

    @Test
    void crearPedido_conStockSuficiente_deberiaCrearPedido() throws StockInsuficienteException {
        UUID productoId = UUID.randomUUID();
        int cantidad = 2;

        when(productoRepository.buscar(productoId)).thenReturn(productoMock);
        when(productoMock.getStock()).thenReturn(5);
        when(productoMock.getNombre()).thenReturn("Producto X");

        Map<UUID, Integer> productos = new HashMap<>();
        productos.put(productoId, cantidad);

        IPedido pedido = useCase.crearPedido(productos);

        assertNotNull(pedido);
        verify(pedidoRepository).guardar(pedido);
    }

    @Test
    void crearPedido_conStockInsuficiente_deberiaLanzarExcepcion() {
        UUID productoId = UUID.randomUUID();
        int cantidad = 10;

        when(productoRepository.buscar(productoId)).thenReturn(productoMock);
        when(productoMock.getStock()).thenReturn(5);
        when(productoMock.getNombre()).thenReturn("Producto X");

        Map<UUID, Integer> productos = new HashMap<>();
        productos.put(productoId, cantidad);

        assertThrows(StockInsuficienteException.class, () -> useCase.crearPedido(productos));
    }
}
