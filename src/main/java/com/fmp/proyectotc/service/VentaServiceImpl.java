package com.fmp.proyectotc.service;

import com.fmp.proyectotc.model.DetalleVenta;
import com.fmp.proyectotc.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fmp.proyectotc.model.Venta;
import com.fmp.proyectotc.repository.VentaRepository;
import java.util.HashMap;
import java.util.List;



@Service
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoService productoService;

    public VentaServiceImpl(VentaRepository ventaRepository, ProductoService productoService, HashMap<Long, Integer> productoCantidad) {
        this.ventaRepository = ventaRepository;
        this.productoService = productoService;
    }

    @Override
    @Transactional
    public Venta saveVenta(Venta venta) {
        double total = 0.0;

        for (DetalleVenta detalle : venta.getDetallesVenta()) {
            Producto producto = productoService.getProductoById(detalle.getProducto().getCodigo_producto());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + detalle.getProducto().getCodigo_producto());
            }

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            total += producto.getPrecio() * detalle.getCantidad();
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoService.saveProducto(producto);

            detalle.setVenta(venta);
            detalle.setProducto(producto);
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public void deleteVenta(Long id_venta) {
        Venta venta = ventaRepository.findById(id_venta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        for (DetalleVenta detalle : venta.getDetallesVenta()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoService.saveProducto(producto);
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


        for (DetalleVenta detalle : ventaOriginal.getDetallesVenta()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoService.saveProducto(producto);
        }

        ventaOriginal.getDetallesVenta().clear();

        double total = 0.0;
        for (DetalleVenta nuevoDetalle : nuevaVenta.getDetallesVenta()) {
            Producto producto = productoService.getProductoById(nuevoDetalle.getProducto().getCodigo_producto());
            if (producto == null) {
                throw new RuntimeException("Producto no encontrado: " + nuevoDetalle.getProducto().getCodigo_producto());
            }

            if (producto.getStock() < nuevoDetalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            total += producto.getPrecio() * nuevoDetalle.getCantidad();
            producto.setStock(producto.getStock() - nuevoDetalle.getCantidad());
            productoService.saveProducto(producto);

            nuevoDetalle.setVenta(ventaOriginal);
            nuevoDetalle.setProducto(producto);
            ventaOriginal.getDetallesVenta().add(nuevoDetalle);
        }

        ventaOriginal.setTotal(total);
        return ventaRepository.save(ventaOriginal);
    }
}