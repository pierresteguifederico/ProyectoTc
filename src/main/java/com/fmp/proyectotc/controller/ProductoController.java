package com.fmp.proyectotc.controller;

import com.fmp.proyectotc.model.Producto;
import com.fmp.proyectotc.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.getAllProductos();

    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearProducto(@RequestBody Producto producto) {
        try {
            productoService.saveProducto(producto);
            return new ResponseEntity<>(producto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{codigo_producto}")
    public void deleteArticulo(@PathVariable Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);
    }


    @GetMapping("/id/{codigo_producto}")
    public ResponseEntity<Producto> obtenerProductos (@PathVariable Long codigo_producto) {
        Producto producto = productoService.getProductoById(codigo_producto);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    @PutMapping("/editar/{codigo_producto}")
    public ResponseEntity<Object> modificarProducto(@PathVariable Long codigo_producto, @RequestBody Producto producto) {
        try {
            Producto productoExistente = productoService.getProductoById(codigo_producto);
            if (productoExistente == null) {
                return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }
            producto.setCodigo_producto(codigo_producto);
            productoService.saveProducto(producto);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}





