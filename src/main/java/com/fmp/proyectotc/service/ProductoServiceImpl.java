package com.fmp.proyectotc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fmp.proyectotc.model.Producto;
import com.fmp.proyectotc.repository.ProductoRepository;
import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;}

    @Override
    @Transactional
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }


    @Override
    @Transactional
    public void deleteProducto(Long codigo_producto) {
        productoRepository.deleteById(codigo_producto);
    }

    @Override
    public Producto getProductoById(Long codigo_producto) {
       return productoRepository.findById(codigo_producto).orElse(null);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
}
