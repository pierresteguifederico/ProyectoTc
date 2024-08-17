package com.fmp.proyectotc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Entity @Data @Table (name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre debe contener solo letras y espacios")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido debe contener solo letras y espacios")
    private String apellido;
    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[0-9]$", message = "El DNI tiene que contener solo numeros")
    private String dni;
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Venta> ventas;}





