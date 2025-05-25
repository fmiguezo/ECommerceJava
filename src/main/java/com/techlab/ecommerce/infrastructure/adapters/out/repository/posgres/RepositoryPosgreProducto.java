package com.techlab.ecommerce.infrastructure.adapters.out.repository.posgres;

import com.techlab.ecommerce.domain.exceptions.ProductoYaExistenteException;
import com.techlab.ecommerce.domain.model.producto.IProducto;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.entities.ProductoEntity;
import com.techlab.ecommerce.infrastructure.adapters.out.persistence.mappers.ProductoPersistenceMapper;
import com.techlab.ecommerce.infrastructure.ports.out.IProductoRepository;
import com.techlab.ecommerce.infrastructure.ports.out.JPAProductoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class RepositoryPosgreProducto implements IProductoRepository {
    private final JPAProductoRepository jpaProductoRepository;
    private final ProductoPersistenceMapper productoPersistenceMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public RepositoryPosgreProducto(JPAProductoRepository jpaProductoRepository,
                                     ProductoPersistenceMapper productoPersistenceMapper) {
        this.jpaProductoRepository = jpaProductoRepository;
        this.productoPersistenceMapper = productoPersistenceMapper;
    }

    @Override
    public void crear(IProducto producto) throws ProductoYaExistenteException {
        if (jpaProductoRepository.existsByNombre(producto.getNombre())) {
            throw new ProductoYaExistenteException("El producto ya existe: " + producto.getNombre());
        }
        ProductoEntity productoEntity = productoPersistenceMapper.toEntity(producto);
        jpaProductoRepository.save(productoEntity);
    }

    @Override
    public List<IProducto> obtenerTodos() {
        return jpaProductoRepository.findAll()
                .stream()
                .map(productoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<IProducto> buscar(UUID id) {
        return jpaProductoRepository.findById(id)
                .map(productoPersistenceMapper::toDomain);
    }

    @Override
    public Optional<IProducto> buscar(String nombre) {
        return jpaProductoRepository.findAll()
                .stream()
                .filter(producto -> producto.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .map(productoPersistenceMapper::toDomain);
    }

    @Override
    public void eliminar(UUID id) {
        if (!jpaProductoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        jpaProductoRepository.deleteById(id);
    }
}
