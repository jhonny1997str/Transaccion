package com.Transaccion.service;

import com.Transaccion.model.Clientes;
import com.Transaccion.model.Pedidos;
import com.Transaccion.repository.ClienteRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//este servicio es una interfaz debido ala abstraccion,varias impletaciones,pruebas ,organizacion
public interface PedidosService {//esta interfaz permite gestionar realizar las operaciones para gestionar los clientews
    public List<Pedidos> findAll();
    public Pedidos findById(Long id);
    public Pedidos save(Pedidos pedidos);

    public Pedidos update(Long id, Pedidos pedidos);
    public void delete(Long id);



}
