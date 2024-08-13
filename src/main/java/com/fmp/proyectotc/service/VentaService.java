package com.fmp.proyectotc.service;

import com.fmp.proyectotc.model.Venta;
import java.util.List;

public interface VentaService {
    Venta saveVenta(Venta venta);
    void deleteVenta(Long id);
    Venta getVentaById(Long id);
    List<Venta> getAllVentas();

}
