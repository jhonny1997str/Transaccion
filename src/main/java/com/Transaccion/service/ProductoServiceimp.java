package com.Transaccion.service;

import com.Transaccion.model.Clientes;
import com.Transaccion.model.Productos;
import com.Transaccion.repository.ProductosRepository;
import org.springframework.stereotype.Service;
import  lombok.*;

import org.springframework.transaction.annotation.*;

import java.util.List;

@Service //esta clase ess logica de negocio
//este genera un constructor que inicia dependencias finales es decir es mutable y seguro
// no es necesario getter o setter ya que son inmutables
@RequiredArgsConstructor
public class ProductoServiceimp implements ProductoService {
    final ProductosRepository productosRepository;//repositorio para acceder slos datos de los clientes

    @Override//los metodos que se implementan son los definidos en la interfaz clienteservice
    //TRANSACCION: Es un conjunto de operaciones que se deben realizar todas juntas.
// Si una de ellas falla, todas deben revertirse.
// Esto asegura que los datos no queden en un estado inconsistente.
    @Transactional(readOnly = true)//este metodo solo lee datos y no realiza cambios
    public List<Productos> findAll(){return  productosRepository.findAll();}

    @Override
    @Transactional(readOnly = true)
    public Productos findById(Long id){return productosRepository.findById(id).orElse(null);}

    @Override
    //readOnly = false : El método puede manejar transacciones que realicen cambios en la base de datos.
    //ROLLBACKFOR :anotacion de @transactional  que define que excepciones deben provocar un rollback
    //de la transaccion actual..si se lanza la excpecion durante le metodo transaccionado todas
    //las operaciones se deshacen manteniendo la consistencia
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Productos save(Productos productos){return  productosRepository.save(productos);}

    @Override
    //La PROPAGACION se refiere a cómo se comportan las transacciones en situaciones donde un método
// que tiene transacciones llama a otro método que también tiene transacciones.
    //REQUIRED: Usa la transacción existente o crea una nueva.
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Productos update(Long id, Productos productos){
        Productos clientestmp = productosRepository.findById(id)//variable temporal buscar cliente por id
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
        //actualizar datos del cliente
        clientestmp.setProductName(productos.getProductName());
        clientestmp.setPrice(productos.getPrice());
        return productosRepository.save(clientestmp);//guarda y devuelve el cliente actualizado

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Long id){productosRepository.deleteById(id);}

    @Override
    @Transactional(readOnly = true)
    public Productos findByProductName(String productName){
        //llama al metodo del repositorio y lanza expecion en caso
        return productosRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
    }



}

