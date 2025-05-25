package com.techlab.ecommerce.domain.model.producto;

public class ProductoFactory {
    /**
     * Factory method para crear un producto.
     * Por ahora no tenemos diferentes tipos de productos, pero lo dejamos para que sea escalable si en el futuro los tenemos
     */
    public static IProducto crearProducto(String nombre, double precio, int stock) {
        return new Producto(nombre, precio, stock);
    }
}
