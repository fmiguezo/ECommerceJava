package application;

class ObtenerDetalleProductoUseCaseTest {

/*    @Mock
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
    }*/
}
