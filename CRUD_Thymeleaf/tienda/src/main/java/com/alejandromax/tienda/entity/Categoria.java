package com.alejandromax.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoría no puede ir vacío.")
    @Size(min = 2, max = 60, message = "El nombre debe tener entre 2 y 60 caracteres.")
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @NotBlank(message = "La descripción no puede ir vacía.")
    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
}