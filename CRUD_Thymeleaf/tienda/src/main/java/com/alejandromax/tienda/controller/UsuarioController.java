package com.alejandromax.tienda.controller;

import com.alejandromax.tienda.entity.Usuario;
import com.alejandromax.tienda.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("usuario", new Usuario());
        return "usuarios";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Usuario usuario) {
        usuarioService.actualizar(usuario.getIdUsuario(), usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        try {
            Usuario usuario = usuarioService.obtenerPorId(id);
            if (usuario != null) {
                model.addAttribute("usuarios", List.of(usuario));
            } else {
                model.addAttribute("usuarios", List.of());
            }
        } catch (Exception e) {
            model.addAttribute("usuarios", List.of());
        }
        model.addAttribute("usuario", new Usuario());
        return "usuarios";
    }
}