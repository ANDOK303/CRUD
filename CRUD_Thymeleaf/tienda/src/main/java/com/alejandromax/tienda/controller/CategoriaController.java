package com.alejandromax.tienda.controller;

import com.alejandromax.tienda.entity.Categoria;
import com.alejandromax.tienda.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "categorias";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria) {
        categoriaService.crear(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Categoria categoria) {
        categoriaService.actualizar(categoria.getIdCategoria(), categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        model.addAttribute("categorias", categoria != null ? List.of(categoria) : List.of());
        return "categorias";
    }
}