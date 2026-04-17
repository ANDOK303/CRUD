package com.alejandromax.tienda.service;

import com.alejandromax.tienda.entity.Categoria;
import com.alejandromax.tienda.exception.ResourceNotFoundException;
import com.alejandromax.tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría con ID no encontrado: " + id));
    }

    @Override
    public Categoria crear(Categoria categoria) {
        categoria.setIdCategoria(null);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Integer id, Categoria categoria) {
        Categoria existente = obtenerPorId(id);
        existente.setNombreCategoria(categoria.getNombreCategoria());
        existente.setDescripcionCategoria(categoria.getDescripcionCategoria());
        return categoriaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría con ID no encontrado: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}