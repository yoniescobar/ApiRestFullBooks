package com.intecap.Apibooks.service;

import com.intecap.Apibooks.model.Libro;
import com.intecap.Apibooks.model.dao.ILibroDao;
import com.intecap.Apibooks.response.CategoriaResponseRest;
import com.intecap.Apibooks.response.LibroResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service // @Service: indica que es un servicio
public class LibroServiceImpl implements ILibroService {

    private static final Logger log = Logger.getLogger(CategoriaServiceImpl.class.getName());

    @Autowired // @Autowired: inyecta el repositorio de libros para poder utilizarlo en este servicio
    private ILibroDao libroDao;


    @Override
    @Transactional(readOnly = true) // @Transactional: indica que este metodo se ejecutara dentro de una transaccion
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        log.info("Buscando todos los libros");

        LibroResponseRest response = new LibroResponseRest(); // Creamos un objeto de tipo LibroResponseRest para guardar la respuesta que se le dara al cliente

        try{
            List<Libro> listLibros = (List<Libro>) libroDao.findAll(); // Buscamos todos los libros de la base de datos
            response.getLibroResponse().setLibros(listLibros); // Guardamos la lista de libros en la respuesta
            response.setMetadata("Respuesta exitosa", "200", "Lista de Libros"); // Guardamos los metadatos de la respuesta

        }catch (Exception e){
            log.severe("Error al buscar los libros: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al buscar los libros", "500", e.getMessage()); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200


    }
}
