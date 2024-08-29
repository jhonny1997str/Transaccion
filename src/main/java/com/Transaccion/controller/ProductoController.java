package com.Transaccion.controller;

import com.Transaccion.model.Clientes;
import com.Transaccion.model.Productos;
import com.Transaccion.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {
    private  final ProductoService productoService;

    @GetMapping
    public List<Productos> findAll(){return productoService.findAll();}

    @GetMapping("/{id}")
    //@pathvariable para capturar valores de la ruta y es  parametro que vienen desde la url
    public Productos findById(@PathVariable Long id){return  productoService.findById(id);}

    @PostMapping()
    //@RequestBody indica cuerpo de una solicitud HTTP debe ser convertido a objeto java
    public Productos save(@RequestBody Productos productos){return  productoService.save(productos);}

    @PutMapping("/{id}")
    //@PATHVARIABLE RECIBE EL ID DEL CLIENTE DESDE LA RUTA, @REQUESTBODY RECIBE EL OBJETO DESDE EL CUERPO DE
    //LA SOLICITUD HTTP.
    public Productos update(@PathVariable Long id, @RequestBody Productos productos){
        return  productoService.update(id, productos);
    }

    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable Long id){
        productoService.delete(id);}


}
