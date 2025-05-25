package application;

import com.techlab.ecommerce.application.usecase.ListarProductosUseCase;
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
import java.util.UUID;

class ListarProductosUseCaseTest {

    @Mock
    IProductoService productoService;

    @InjectMocks
    ListarProductosUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ListarProductosUseCase(productoService);
    }

    @Test
    void ejecutar_deberiaRetornarListaProductoDTO() {
        IProducto producto = mock(IProducto.class);
        when(producto.getId()).thenReturn(UUID.randomUUID());
        when(producto.getNombre()).thenReturn("Producto");
        when(producto.getPrecio()).thenReturn(100.0);
        when(producto.getStock()).thenReturn(10);

        when(productoService.listarProductos()).thenReturn(List.of(producto));

        var resultado = useCase.ejecutar();

        assertEquals(1, resultado.size());
        verify(productoService).listarProductos();
    }
}
