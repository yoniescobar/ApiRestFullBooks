package com.intecap.Apibooks.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity // indica que es una entidad
@Table(name = "categoria") // nombre de la tabla en la base de datos de JPA
public class Categoria  implements Serializable { // Serializable: sera una clase bean, para que pueda ser convertida a JSON

    private static final long serialVersionUID = 1L; // identificador unico de la clase autogenerado
    @Id // jakarta.persistence java persistence api (JPA) primera opcion
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Strategy: es una interfaz que define una serie de algoritmos para resolver un problema. java.io.Serializable
    private Long id;
    private String nombre;
    private String descripcion;

    // Getters y Setters excepto el Serializable, ya que es autogenerado

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

}
