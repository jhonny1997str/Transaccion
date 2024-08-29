package com.Transaccion.service;
import com.Transaccion.model.Clientes;
import com.Transaccion.repository.ClienteRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//este servicio es una interfaz debido ala abstraccion,varias impletaciones,pruebas ,organizacion
public interface ClienteService {//esta interfaz permite gestionar realizar las operaciones para gestionar los clientews
    public List<Clientes> findAll();
    public Clientes findById(Long id);
    public Clientes save(Clientes clientes);
    public Clientes update(Long id, Clientes clientes);
    public void delete(Long id);
    public Clientes findByName(String nombre);


}
