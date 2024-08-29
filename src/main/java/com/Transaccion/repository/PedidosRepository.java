package com.Transaccion.repository;

import com.Transaccion.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    // No hay método necesario para buscar por nombre del cliente aquí
    // Puedes agregar métodos específicos según tus necesidades
}
