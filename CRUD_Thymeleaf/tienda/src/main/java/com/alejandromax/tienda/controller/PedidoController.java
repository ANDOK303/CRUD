package com.alejandromax.tienda.controller;

import com.alejandromax.tienda.entity.Pedido;
import com.alejandromax.tienda.service.PedidoService;
import com.alejandromax.tienda.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    public PedidoController(PedidoService pedidoService, UsuarioService usuarioService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("pedido", new Pedido());
        return "pedido";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pedido pedido) {
        pedidoService.crear(pedido);
        return "redirect:/pedidos";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Pedido pedido) {
        pedidoService.actualizar(pedido.getIdPedido(), pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("id") Integer id, Model model) {
        try {
            Pedido p = pedidoService.obtenerPorId(id);
            model.addAttribute("pedidos", List.of(p));
        } catch (Exception e) {
            model.addAttribute("pedidos", List.of());
        }
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("pedido", new Pedido());
        return "pedido";
    }
}