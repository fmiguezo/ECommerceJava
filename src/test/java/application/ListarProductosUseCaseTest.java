package application;

class ListarProductosUseCaseTest {

/*    @Mock
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
    }*/
}
