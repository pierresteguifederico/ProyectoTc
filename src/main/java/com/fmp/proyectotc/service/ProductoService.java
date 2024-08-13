package com.fmp.proyectotc.service;


import com.fmp.proyectotc.model.Producto;
import java.util.List;

public interface ProductoService {
    Producto saveProducto(Producto producto);
    Producto getProductoById(Long codigo);
    void deleteProducto(Long codigo);
    List<Producto> getAllProductos();
}
