package com.alejandromax.tienda.controller;

import com.alejandromax.tienda.entity.Producto;
import com.alejandromax.tienda.service.ProductoService;
import com.alejandromax.tienda.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("producto", new Producto());
        return "producto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.crear(producto);
        return "redirect:/producto";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Producto producto) {
        productoService.actualizar(producto.getIdProducto(), producto);
        return "redirect:/producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/producto";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("id") Integer id, Model model) {
        try {
            Producto p = productoService.obtenerPorId(id);
            model.addAttribute("productos", List.of(p));
        } catch (Exception e) {
            model.addAttribute("productos", List.of());
        }
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("producto", new Producto());
        return "producto";

    }

}
