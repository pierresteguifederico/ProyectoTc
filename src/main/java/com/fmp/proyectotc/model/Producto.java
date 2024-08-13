package com.fmp.proyectotc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
@Entity @Data @Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
    @ManyToMany(mappedBy = "productos")
    @JsonIgnore
    private List<Venta> ventas;


}
