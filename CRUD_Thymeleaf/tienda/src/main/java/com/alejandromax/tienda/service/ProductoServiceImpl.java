package com.alejandromax.tienda.service;

import com.alejandromax.tienda.entity.Producto;
import com.alejandromax.tienda.exception.ResourceNotFoundException;
import com.alejandromax.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = obtenerPorId(id);
        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecioProducto(producto.getPrecioProducto());
        existente.setStock(producto.getStock());
        existente.setCategoria(producto.getCategoria());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) throw new ResourceNotFoundException("ID no existe");
        productoRepository.deleteById(id);
    }
}