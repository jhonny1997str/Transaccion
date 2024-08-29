package com.Transaccion.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos {
    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor se generará automáticamente.
    private Long orderId; // Campo que representa el ID único de la orden.

    @Nullable
    @ManyToOne // Establece una relación de muchos a uno con la entidad Clientes.
    @JoinColumn(name = "customer_id", nullable = false) // Especifica la columna en la tabla 'pedidos' que referencia a 'clientes'.
    private Clientes clientes; // Campo que representa el cliente asociado a esta orden.

    @Nullable
    @ManyToOne // Establece una relación de muchos a uno con la entidad Productos.
    @JoinColumn(name = "product_id", nullable = false) // Especifica la columna en la tabla 'pedidos' que referencia a 'productos'.
    private Productos productos; // Campo que representa el producto asociado a esta orden.

    private LocalDateTime orderDate = LocalDateTime.now(); // Campo que guarda la fecha y hora en que se realizó la orden.
    private Integer quantity; // Campo que representa la cantidad de productos en la orden.
}
