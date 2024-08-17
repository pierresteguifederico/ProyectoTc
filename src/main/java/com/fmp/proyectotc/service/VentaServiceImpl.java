package com.fmp.proyectotc.service;

import com.fmp.proyectotc.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fmp.proyectotc.model.Venta;
import com.fmp.proyectotc.repository.VentaRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoService productoService;
    private final HashMap<Long, Integer> productoCantidad;

    public VentaServiceImpl(VentaRepository ventaRepository, ProductoService productoService, HashMap<Long, Integer> productoCantidad) {
        this.ventaRepository = ventaRepository;
        this.productoService = productoService;
        this.productoCantidad = productoCantidad;
    }

    @Override
    @Transactional
    public Venta saveVenta(Venta venta) {

        double total = 0;
        productoCantidad.clear();

        for (Producto producto : venta.getProductos()) {
            Long productoId = producto.getCodigo_producto();
            productoCantidad.put(productoId, productoCantidad.getOrDefault(productoId, 0) + 1);}

        for (Map.Entry<Long, Integer> entry : productoCantidad.entrySet()) {
            Long productoId = entry.getKey();
            int cantidadVendida = entry.getValue();

            Producto producto = productoService.getProductoById(productoId);
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + productoId);
            }

            if (producto.getStock() < cantidadVendida) {
                throw new RuntimeException("Stock insuficiente para el producto: " + productoId);
            }

            total += producto.getPrecio() * cantidadVendida;
            producto.setStock(producto.getStock() - cantidadVendida);
            productoService.saveProducto(producto);}

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public void deleteVenta(Long id_venta) {
        Venta venta = ventaRepository.findById(id_venta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        for (Producto producto : venta.getProductos()) {
            Long productoId = producto.getCodigo_producto();
            int cantidadVendida = productoCantidad.getOrDefault(productoId, 0);
            Producto prod = productoService.getProductoById(productoId);

            if (prod != null) {
                prod.setStock(prod.getStock() + cantidadVendida);
                productoService.saveProducto(prod);
                productoCantidad.clear();
            }
        }
        ventaRepository.deleteById(id_venta);
    }

    @Override
    public Venta getVentaById(Long id_venta) {
        return ventaRepository.findById(id_venta).orElse(null);
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Transactional
    public Venta updateVenta(Long id_venta, Venta nuevaVenta) {

        Venta ventaOriginal = ventaRepository.findById(id_venta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        for (Map.Entry<Long, Integer> entry : productoCantidad.entrySet()) {
            Long productoId = entry.getKey();
            int cantidadOriginal = entry.getValue();

            Producto producto = productoService.getProductoById(productoId);
            if (producto != null) {
                producto.setStock(producto.getStock() + cantidadOriginal);
                productoService.saveProducto(producto);
            }
        }

        productoCantidad.clear();
        for (Producto producto : nuevaVenta.getProductos()) {
            Long productoId = producto.getCodigo_producto();
            productoCantidad.put(productoId, productoCantidad.getOrDefault(productoId, 0) + 1);
        }

        double total = 0.0;
        for (Map.Entry<Long, Integer> entry : productoCantidad.entrySet()) {
            Long productoId = entry.getKey();
            int cantidadVendida = entry.getValue();

            Producto producto = productoService.getProductoById(productoId);
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + productoId);
            }

            if (producto.getStock() < cantidadVendida) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            total += producto.getPrecio() * cantidadVendida;
            producto.setStock(producto.getStock() - cantidadVendida);
            productoService.saveProducto(producto);
        }

        ventaOriginal.setProductos(nuevaVenta.getProductos());
        ventaOriginal.setTotal(total);
        return ventaRepository.save(ventaOriginal);
    }}