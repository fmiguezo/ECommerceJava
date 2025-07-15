package com.techlab.ecommerce.infrastructure.adapters.out.repository.memoria;

import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.infrastructure.ports.out.IProductoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Profile("memory")
@Component
public class RepositoryMemoriaProducto implements IProductoRepository {

    private final Map<UUID, IProducto> productos = new ConcurrentHashMap<>();
    private final Map<String, UUID> nombresIds = new ConcurrentHashMap<>();

    @Override
    public IProducto save(IProducto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto no puede ser nulo");
        }

        if (nombresIds.containsKey(producto.getNombre().toLowerCase())) {
            throw new IllegalArgumentException("Ya existe un producto con este nombre");
        }

        productos.put(producto.getId(), producto);
        nombresIds.put(producto.getNombre().toLowerCase(), producto.getId());
        return producto;
    }

    @Override
    public Optional<IProducto> findById(UUID id) {
        return Optional.ofNullable(productos.get(id));
    }

    @Override
    public Optional<IProducto> findByNombre(String nombre) {
        return Optional.ofNullable(nombresIds.get(nombre.toLowerCase()))
                .map(productos::get);
    }

    @Override
    public List<IProducto> findAll() {
        return new ArrayList<>(productos.values());
    }

    @Override
    public void deleteById(UUID id) {
        IProducto producto = productos.get(id);
        if (producto != null) {
            nombresIds.remove(producto.getNombre().toLowerCase());
            productos.remove(id);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return productos.containsKey(id);
    }
}