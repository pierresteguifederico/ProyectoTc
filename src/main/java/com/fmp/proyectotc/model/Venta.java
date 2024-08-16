package com.fmp.proyectotc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
@Entity @Data @Table (name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venta;
    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "cliente_id",referencedColumnName = "id_cliente")
    private Cliente cliente;
    @ManyToMany
    @JoinTable(name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

}


