package com.fmp.proyectotc.controller;

import com.fmp.proyectotc.model.Venta;
import com.fmp.proyectotc.service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> obtenerVentas() {
        return ventaService.getAllVentas();
    }
    @GetMapping("/{id}")
    public Venta getArticuloById(@PathVariable Long codigo_venta) {
        return ventaService.getVentaById(codigo_venta);
    }

    @PostMapping("/crear")
    public Venta createArticulo(@RequestBody Venta venta) {
        return ventaService.saveVenta(venta);
    }

    @DeleteMapping("/{id}")
    public void deleteArticulo(@PathVariable Long codigo_venta) {
        ventaService.deleteVenta(codigo_venta);
    }
}