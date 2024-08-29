package com.Transaccion.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "clientes")
@AllArgsConstructor // Genera constructor con parámetros
@NoArgsConstructor // Genera constructor sin parámetros
public class Clientes {
    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor de la clave primaria automáticamente.
    private Long customerId; // Campo que representa la clave primaria.

    private String customerName; // Campo que representa el nombre del cliente.
    private String email; // Campo que representa el correo electrónico del cliente.
    private String phoneNumber; // Campo que representa el número de teléfono del cliente.
}
//{
//    "customerName": "Juan Pérez",
//    "email": "juan.perez@example.com",
//    "phoneNumber": "123456789"
//}