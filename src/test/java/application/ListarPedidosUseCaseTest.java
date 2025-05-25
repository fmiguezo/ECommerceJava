package application;

import com.techlab.ecommerce.adapters.out.repository.IPedidoRepository;
import com.techlab.ecommerce.application.usecase.ListarPedidosUseCase;
import com.techlab.ecommerce.domain.model.pedido.IPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class ListarPedidosUseCaseTest {

    @Mock
    IPedidoRepository pedidoRepository;

    @InjectMocks
    ListarPedidosUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ListarPedidosUseCase(pedidoRepository);
    }

    @Test
    void ejecutar_deberiaRetornarListaPedidosDTO() {
        IPedido pedidoMock = mock(IPedido.class);
        when(pedidoRepository.obtenerTodos()).thenReturn(List.of(pedidoMock));

        var resultado = useCase.ejecutar();

        assertEquals(1, resultado.size());
        verify(pedidoRepository).obtenerTodos();
    }
}
