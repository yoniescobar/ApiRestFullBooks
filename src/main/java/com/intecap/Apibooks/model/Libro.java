package com.intecap.Apibooks.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "libro")
public class Libro implements Serializable { //Serializable: es una interfaz que define una serie de algoritmos para resolver un problema. java.io.Serializable

    private static final Long serialVersionUID = 1L; //identificador unico de la clase autogenerado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

    // Relacion de muchos a uno / un libro puede tener una categoria, pero una categoria puede tener muchos libros
    @ManyToOne
    @JoinColumn(name = "categoria_id") // nombre de la columna en la tabla libro
    Categoria categoria; // objeto de tipo categoria

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
