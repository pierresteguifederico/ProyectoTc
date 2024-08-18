package com.fmp.proyectotc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Entity @Data @Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "codigo_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    @JsonBackReference
    private Venta venta;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;
}
