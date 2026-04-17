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
        model.addAttribute("categoria", new Categoria());
        return "categoria";
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

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        try {
            Categoria categoria = categoriaService.obtenerPorId(id);
            if (categoria != null) {
                model.addAttribute("categorias", List.of(categoria));
            } else {
                model.addAttribute("categorias", List.of());
            }
        } catch (Exception e) {
            model.addAttribute("categorias", List.of());
        }
        model.addAttribute("categoria", new Categoria());
        return "categoria";
    }
}