package com.fmp.proyectotc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre solo puede contener letras y espacios")
    private String nombre;
    @NotBlank(message = "La marca no puede estar vacía")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "La marca debe contener solo letras y espacios")
    private String marca;
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private Double precio;
    @NotNull
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetalleVenta> detallesVenta;

}
