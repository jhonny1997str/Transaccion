package com.Transaccion.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; // Campo que representa la clave primaria del producto.

    private String productName; // Campo que representa el nombre del producto.
    private Double price; // Campo que representa el precio del producto.
}
