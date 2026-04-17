package com.alejandromax.tienda.service;

import com.alejandromax.tienda.entity.DetallePedido;
import java.util.List;

public interface DetallePedidoService {
    List<DetallePedido> listar();
    DetallePedido obtenerPorId(Integer id);
    DetallePedido crear(DetallePedido detalle);
    DetallePedido actualizar(Integer id, DetallePedido detalle);
    void eliminar(Integer id);
}