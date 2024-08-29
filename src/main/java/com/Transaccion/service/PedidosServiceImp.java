package com.Transaccion.service;



import com.Transaccion.model.Pedidos;
import com.Transaccion.repository.PedidosRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidosServiceImp implements PedidosService {

    private final PedidosRepository pedidosRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findAll() {
        return pedidosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedidos findById(Long id) {
        return pedidosRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Pedidos save(Pedidos pedidos) {
        return pedidosRepository.save(pedidos);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Pedidos update(Long id, Pedidos pedidos) {
        Pedidos pedidostmp = pedidosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido not found"));
        // Actualizar datos del pedido
        pedidostmp.setQuantity(pedidos.getQuantity());
        pedidostmp.setClientes(pedidos.getClientes()); // Ejemplo de actualización de cliente
        pedidostmp.setProductos(pedidos.getProductos());
        // Actualizar otros campos relevantes de la entidad Pedidos
        // pedidostmp.setOtroCampo(pedidos.getOtroCampo());
        return pedidosRepository.save(pedidostmp);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        pedidosRepository.deleteById(id);
    }
}
