package application;

import com.techlab.ecommerce.application.usecase.GestionarProductoUseCase;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class GestionarProductoUseCaseTest {

    @Mock
    IProductoService productoService;

    @InjectMocks
    GestionarProductoUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new GestionarProductoUseCase(productoService);
    }

    @Test
    void crearProducto_deberiaLlamarServicio() throws Exception {
        when(productoService.crearProducto("Nombre", 100.0, 10)).thenReturn(mock(IProducto.class));

        IProducto resultado = useCase.crearProducto("Nombre", 100.0, 10);

        assertNotNull(resultado);
        verify(productoService).crearProducto("Nombre", 100.0, 10);
    }

    @Test
    void listarProductos_deberiaRetornarLista() {
        List<IProducto> mockLista = List.of(mock(IProducto.class));
        when(productoService.listarProductos()).thenReturn(mockLista);

        List<IProducto> resultado = useCase.listarProductos();

        assertEquals(1, resultado.size());
        verify(productoService).listarProductos();
    }
}
