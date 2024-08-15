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

    public VentaServiceImpl(VentaRepository ventaRepository, ProductoService productoService) {
        this.ventaRepository = ventaRepository;
        this.productoService = productoService;
    }
    @Override
    @Transactional
    public Venta saveVenta(Venta venta) {
        // Calcular el total de la venta basado en el precio y la cantidad de cada producto
        double total = 0.0;
        // Usar un mapa para contar la cantidad de cada producto en la venta
        Map<Long, Integer> productoCantidad = new HashMap<>();
        for (Producto producto : venta.getProductos()) {
            Long productoId = producto.getCodigo_producto();
            productoCantidad.put(productoId, productoCantidad.getOrDefault(productoId, 0) + 1);
        }

        // Verificar y actualizar el stock de cada producto
        for (Map.Entry<Long, Integer> entry : productoCantidad.entrySet()) {
            Long productoId = entry.getKey();
            int cantidadVendida = entry.getValue();

            Producto producto = productoService.getProductoById(productoId);
            if (producto == null) {
                try {
                    throw new Exception("Producto no encontrado: " + productoId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // Verificar si hay suficiente stock
            if (producto.getStock() < cantidadVendida) {
                try {
                    throw new Exception("Stock insuficiente para el producto: " + producto.getNombre());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // Actualizar el total y el stock del producto
            total += producto.getPrecio() * cantidadVendida;
            producto.setStock(producto.getStock() - cantidadVendida);
            productoService.saveProducto(producto);
        }

        // Establecer el total calculado en la venta
        venta.setTotal(total);
        return ventaRepository.save(venta);}

    @Override
    @Transactional
    public void deleteVenta(Long codigo_venta) {
        ventaRepository.deleteById(codigo_venta);}

    @Override
    public Venta getVentaById(Long codigo_venta) {
        return  ventaRepository.findById(codigo_venta).orElse(null);    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }
}
