package com.intecap.Apibooks.service;

import com.intecap.Apibooks.model.Categoria;
import com.intecap.Apibooks.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService { //Interface para acceder a los datos de la tabla categoria de la base de datos

    public ResponseEntity<CategoriaResponseRest>  buscarCategorias(); //metodo para buscar todas las categorias de la base de datos
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaId(Long id); //metodo para buscar una categoria por su id

    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria); //metodo para crear una categoria

    public ResponseEntity<CategoriaResponseRest> actualizar(Long id, Categoria categoria); //metodo para actualizar una categoria

    public ResponseEntity<CategoriaResponseRest> eliminar(Long id); //metodo para eliminar una categoria
}
