package com.Transaccion.repository;

import com.Transaccion.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    // Definir este método para encontrar cliente por su nombre
    //Containing: Permite buscar cualquier cliente cuyo nombre contenga el texto dado
    // (no solo coincidencias exactas).
    //IgnoreCase: Ignora las diferencias entre mayúsculas y minúsculas al buscar.
    Optional<Clientes> findByCustomerNameContainingIgnoreCase(String nombre);
}
