package com.intecap.Apibooks.response;

import com.intecap.Apibooks.model.Libro;

import java.util.List;

public class LibroResponse {

    private List<Libro> libros;
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
