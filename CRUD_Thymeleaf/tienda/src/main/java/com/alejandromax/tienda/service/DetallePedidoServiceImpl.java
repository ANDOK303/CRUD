package com.alejandromax.tienda.service;

import com.alejandromax.tienda.entity.DetallePedido;
import com.alejandromax.tienda.exception.ResourceNotFoundException;
import com.alejandromax.tienda.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detalleRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<DetallePedido> listar() {
        return detalleRepository.findAll();
    }

    @Override
    public DetallePedido obtenerPorId(Integer id) {
        return detalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado: " + id));
    }

    @Override
    public DetallePedido crear(DetallePedido detalle) {
        return detalleRepository.save(detalle);
    }

    @Override
    public DetallePedido actualizar(Integer id, DetallePedido detalle) {
        DetallePedido existente = obtenerPorId(id);
        existente.setCantidad(detalle.getCantidad());
        existente.setPrecioUnitario(detalle.getPrecioUnitario());
        existente.setPedido(detalle.getPedido());
        existente.setProducto(detalle.getProducto());
        return detalleRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!detalleRepository.existsById(id)) throw new ResourceNotFoundException("ID no existe");
        detalleRepository.deleteById(id);
    }
}