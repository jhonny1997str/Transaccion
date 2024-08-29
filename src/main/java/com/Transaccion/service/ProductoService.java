package com.Transaccion.service;

import com.Transaccion.model.Clientes;
import com.Transaccion.model.Productos;
import com.Transaccion.repository.ClienteRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//este servicio es una interfaz debido ala abstraccion,varias impletaciones,pruebas ,organizacion
public interface ProductoService {//esta interfaz permite gestionar realizar las operaciones para gestionar los clientews
    public List<Productos> findAll();
    public Productos findById(Long id);
    public Productos save(Productos productos);
    public Productos update(Long id, Productos productos);
    public void delete(Long id);
    public Productos findByProductName(String productName);


}