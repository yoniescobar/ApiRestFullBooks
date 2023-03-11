package com.intecap.Apibooks.service;

import com.intecap.Apibooks.response.CategoriaResponseRest;

public interface ICategoriaService { //Interface para acceder a los datos de la tabla categoria de la base de datos

    public CategoriaResponseRest buscarCategorias(); //metodo para buscar todas las categorias de la base de datos
}
