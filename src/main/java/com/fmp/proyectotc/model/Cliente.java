package com.fmp.proyectotc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Entity @Data @Table (name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String dni;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Venta> ventas;}





