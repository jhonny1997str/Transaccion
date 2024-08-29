package com.Transaccion.repository;

import com.Transaccion.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {
    // Definir este método para encontrar un producto por su nombre
    Optional<Productos> findByProductName(String productName);
}
