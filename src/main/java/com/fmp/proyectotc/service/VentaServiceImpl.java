package com.fmp.proyectotc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fmp.proyectotc.model.Venta;
import com.fmp.proyectotc.repository.VentaRepository;
import java.util.List;


@Service
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;}

    @Override
    @Transactional
    public Venta saveVenta(Venta venta) {
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
