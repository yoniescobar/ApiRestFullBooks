package com.intecap.Apibooks.service;

import com.intecap.Apibooks.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {
    //Interface para acceder a los datos de la tabla libro de la base de datos (CRUD) definicion de metodos

    public ResponseEntity<LibroResponseRest> buscarLibros(); //metodo para buscar todos los libros de la base de datos

}
