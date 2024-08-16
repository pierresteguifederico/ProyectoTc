package com.fmp.proyectotc.controller;

import com.fmp.proyectotc.model.Venta;
import com.fmp.proyectotc.service.VentaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Venta> listarVentas() {
        return ventaService.getAllVentas();
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearVenta(@RequestBody Venta venta) {
        try {
            ventaService.saveVenta(venta);
            return new ResponseEntity<>(venta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id_venta}")
    public void eliminarVenta(@PathVariable Long id_venta) {
        ventaService.deleteVenta(id_venta);
    }

    @GetMapping("/id/{id_venta}")
    public ResponseEntity<Venta> obtenerVenta (@PathVariable Long id_venta) {
        Venta venta = ventaService.getVentaById(id_venta);
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/editar/{id_venta}")
    public ResponseEntity<Object> modificarVenta(@PathVariable Long id_venta, @RequestBody Venta venta) {
        try {
            Venta ventaExistente = ventaService.getVentaById(id_venta);
            if (ventaExistente == null) {
                return new ResponseEntity<>("Venta no encontrada", HttpStatus.NOT_FOUND);
            }
            BeanUtils.copyProperties(venta, ventaExistente,"id_venta");
            ventaService.updateVenta(id_venta,ventaExistente);
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}