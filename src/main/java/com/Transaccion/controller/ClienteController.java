package com.Transaccion.controller;
import com.Transaccion.model.Clientes;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import com.Transaccion.service.ClienteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
    private  final  ClienteService clienteService;

    @GetMapping
    public List<Clientes> findAll(){return clienteService.findAll();}

    @GetMapping("/{id}")
    //@pathvariable para capturar valores de la ruta y es  parametro que vienen desde la url
    public Clientes findById(@PathVariable Long id){return  clienteService.findById(id);}

    @PostMapping()
    //@RequestBody indica cuerpo de una solicitud HTTP debe ser convertido a objeto java
    public Clientes save(@RequestBody Clientes clientes){return  clienteService.save(clientes);}

    @PutMapping("/{id}")
    //@PATHVARIABLE RECIBE EL ID DEL CLIENTE DESDE LA RUTA, @REQUESTBODY RECIBE EL OBJETO DESDE EL CUERPO DE
    //LA SOLICITUD HTTP.
    public Clientes update(@PathVariable Long id, @RequestBody Clientes clientes){
        return  clienteService.update(id, clientes);
    }

    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable Long id){clienteService.delete(id);}

    @GetMapping("/search") //buscar y obtener cliente por nombre
    //@RequestParam se utiliza para capturar parámetros de consulta
    public Clientes findByName(@RequestParam String nombre){
        return clienteService.findByName(nombre);
    }
}
