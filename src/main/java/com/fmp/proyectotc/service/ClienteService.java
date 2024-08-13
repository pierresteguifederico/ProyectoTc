package com.fmp.proyectotc.service;


import com.fmp.proyectotc.model.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente saveCliente(Cliente cliente);
    void deleteCliente(Long id);
    Cliente getClienteById(Long id);
    List<Cliente> getAllClientes();
}
