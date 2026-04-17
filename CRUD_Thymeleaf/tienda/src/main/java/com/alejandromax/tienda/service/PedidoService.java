package com.alejandromax.tienda.service;

import com.alejandromax.tienda.entity.Pedido;
import java.util.List;

public interface PedidoService {
    List<Pedido> listar();
    Pedido obtenerPorId(Integer id);
    Pedido crear(Pedido pedido);
    Pedido actualizar(Integer id, Pedido pedido);
    void eliminar(Integer id);
}