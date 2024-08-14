package com.fmp.proyectotc.controller;

import com.fmp.proyectotc.model.Cliente;
import com.fmp.proyectotc.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearCliente (@RequestBody Cliente cliente) {
        try{
            clienteService.saveCliente(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Error interno del servidor",HttpStatus.INTERNAL_SERVER_ERROR);

        }}

    @GetMapping ("/id/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id_cliente) {
        Cliente cliente = clienteService.getClienteById(id_cliente);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping ("/eliminar/{id_cliente}")
    public void eliminarCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);

    }
    @PutMapping("/editar/{codigo_producto}")
    public ResponseEntity<Object> modificarProducto(@PathVariable Long id_cliente, @RequestBody Cliente cliente) {
        try {
            Cliente clienteExistente = clienteService.getClienteById(id_cliente);
            if (clienteExistente == null) {
                return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }
            cliente.setId_cliente(id_cliente);
            clienteService.saveCliente(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



