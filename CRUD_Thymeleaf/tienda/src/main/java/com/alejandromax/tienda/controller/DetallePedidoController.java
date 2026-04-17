package com.alejandromax.tienda.controller;

import com.alejandromax.tienda.entity.DetallePedido;
import com.alejandromax.tienda.service.DetallePedidoService;
import com.alejandromax.tienda.service.PedidoService;
import com.alejandromax.tienda.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/detalles")
public class DetallePedidoController {

    private final DetallePedidoService detalleService;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoController(DetallePedidoService detalleService, PedidoService pedidoService, ProductoService productoService) {
        this.detalleService = detalleService;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("detalles", detalleService.listar());
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("detalle", new DetallePedido());
        return "detalle-pedido";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetallePedido detalle) {
        detalleService.crear(detalle);
        return "redirect:/detalles";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute DetallePedido detalle) {
        detalleService.actualizar(detalle.getIdDetalle(), detalle);
        return "redirect:/detalles";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        detalleService.eliminar(id);
        return "redirect:/detalles";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("id") Integer id, Model model) {
        try {
            DetallePedido d = detalleService.obtenerPorId(id);
            model.addAttribute("detalles", List.of(d));
        } catch (Exception e) {
            model.addAttribute("detalles", List.of());
        }
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("detalle", new DetallePedido());
        return "detalle-pedido";
    }
}