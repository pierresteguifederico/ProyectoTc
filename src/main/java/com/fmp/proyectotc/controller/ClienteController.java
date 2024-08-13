package com.fmp.proyectotc.controller;

import com.fmp.proyectotc.model.Cliente;
import com.fmp.proyectotc.service.ClienteService;
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
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/crear")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @GetMapping ("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id){
        return clienteService.getClienteById(id);}

    @DeleteMapping ("{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);

    }


}
