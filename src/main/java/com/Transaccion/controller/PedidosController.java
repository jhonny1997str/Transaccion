package com.Transaccion.controller;

import com.Transaccion.model.Pedidos;
import com.Transaccion.model.Productos;
import com.Transaccion.service.PedidosService;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidosController {
    private  final PedidosService pedidosService;

    @GetMapping
    public List<Pedidos> findAll(){return pedidosService.findAll();}

    @GetMapping("/{id}")
    //@pathvariable para capturar valores de la ruta y es  parametro que vienen desde la url
    public Pedidos findById(@PathVariable Long id){return  pedidosService.findById(id);}

    @PostMapping()
    //@RequestBody indica cuerpo de una solicitud HTTP debe ser convertido a objeto java
    public Pedidos save(@RequestBody Pedidos pedidos){return  pedidosService.save(pedidos);}

    @PutMapping("/{id}")
    //@PATHVARIABLE RECIBE EL ID DEL CLIENTE DESDE LA RUTA, @REQUESTBODY RECIBE EL OBJETO DESDE EL CUERPO DE
    //LA SOLICITUD HTTP.
    public Pedidos update(@PathVariable Long id, @RequestBody Pedidos pedidos){
        return  pedidosService.update(id, pedidos);
    }

    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable Long id){
        pedidosService.delete(id);}


}

