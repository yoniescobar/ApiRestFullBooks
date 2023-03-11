package com.intecap.Apibooks.response;

import com.intecap.Apibooks.model.Categoria;

import java.util.List;

public class CategoriaResponse { // clase CategoriaResponse: información para el cliente de como respondio el servicio.

    private List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
