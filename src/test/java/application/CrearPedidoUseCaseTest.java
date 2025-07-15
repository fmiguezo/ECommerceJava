package application;

class CrearPedidoUseCaseTest {

    /*@Mock
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
    }*/
}
