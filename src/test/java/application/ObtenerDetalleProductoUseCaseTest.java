package application;

import com.techlab.ecommerce.application.usecase.ObtenerDetalleProductoUseCase;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.domain.service.producto.IProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

class ObtenerDetalleProductoUseCaseTest {

    @Mock
    IProductoService productoService;

    @InjectMocks
    ObtenerDetalleProductoUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ObtenerDetalleProductoUseCase(productoService);
    }

    @Test
    void ejecutar_conProductoExistente_deberiaRetornarDTO() {
        UUID id = UUID.randomUUID();
        IProducto producto = mock(IProducto.class);
        when(producto.getId()).thenReturn(id);
        when(producto.getNombre()).thenReturn("Producto");
        when(producto.getPrecio()).thenReturn(100.0);
        when(producto.getStock()).thenReturn(5);
        when(productoService.buscarProducto(id)).thenReturn(Optional.of(producto));

        var dto = useCase.ejecutar(id);

        assertNotNull(dto);
        assertEquals("Producto", dto.getNombre());
    }

    @Test
    void ejecutar_conProductoInexistente_deberiaRetornarNull() {
        UUID id = UUID.randomUUID();
        when(productoService.buscarProducto(id)).thenReturn(Optional.empty());

        var dto = useCase.ejecutar(id);

        assertNull(dto);
    }
}
