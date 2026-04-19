package com.alejandromax.tienda.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "nombreUsu")
    private String nombreUsu;

    @Column(name = "apellidoUsu")
    private String apellidoUsu;

    @Column(name = "edadUsu")
    private Integer edadUsu;


    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getApellidoUsu() {
        return apellidoUsu;
    }

    public void setApellidoUsu(String apellidoUsu) {
        this.apellidoUsu = apellidoUsu;
    }

    public Integer getEdadUsu() {
        return edadUsu;
    }

    public void setEdadUsu(Integer edadUsu) {
        this.edadUsu = edadUsu;
    }
}