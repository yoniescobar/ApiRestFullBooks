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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Override
    @Transactional(readOnly = true) // @Transactional: indica que este metodo se ejecutara dentro de una transaccion
    public ResponseEntity<LibroResponseRest> buscarLibroPorId(Long id) {
        log.info("Iniciando metodo buscarPorId () ");

        LibroResponseRest response = new LibroResponseRest(); // Creamos un objeto de tipo LibroResponseRest para guardar la respuesta que se le dara al cliente
        List<Libro> list = new ArrayList<>();
        try {
            Optional<Libro> libro = libroDao.findById(id); // Buscamos el libro por su id

            if(libro.isPresent()) { // Si el libro existe
                list.add(libro.get()); // Agregamos el libro a la lista
                response.getLibroResponse().setLibros(list); // Guardamos la lista de libros en la respuesta
            }else{
                log.severe("No se encontro el libro con el id: " + id);
                response.setMetadata("No se encontro el libro", "404", "No se encontro el libro con el id: " + id); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND); // Retornamos la respuesta con el codigo de error 404
            }

        }catch (Exception e){
            log.severe("Error al buscar el libro: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al buscar el libro", "500","Error al consultar el libro"); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
        }

        response.setMetadata("Respuesta exitosa", "200", "Libro encontrado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200

    }

    @Override
    @Transactional // @Transactional: indica que este metodo se ejecutara dentro de una transaccion
    public ResponseEntity<LibroResponseRest> crear(Libro libro) {
        log.info("Iniciando metodo crear () ");

        LibroResponseRest response = new LibroResponseRest(); // Creamos un objeto de tipo LibroResponseRest para guardar la respuesta que se le dara al cliente
        List<Libro> list = new ArrayList<>();

        try{
            Libro libroGuardado = libroDao.save(libro); // Guardamos el libro en la base de datos

            if(libroGuardado!=null) { // Si el libro se guardo correctamente
                list.add(libroGuardado); // Agregamos el libro a la lista
                response.getLibroResponse().setLibros(list); // Guardamos la lista de libros en la respuesta
            }else {
                log.severe("Error al guardar el libro");
                response.setMetadata("Error al guardar el libro", "500", "Error al guardar el libro"); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
            }

        }catch (Exception e){
            log.severe("Error al guardar el libro: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al guardar el libro", "500", "Error al Guaradr el libro"); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500

        }

        response.setMetadata("Respuesta exitosa", "200", "Libro creado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200

    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {
        log.info("Iniciando metodo actualizar () ");

        LibroResponseRest response = new LibroResponseRest(); // Creamos un objeto de tipo LibroResponseRest para guardar la respuesta que se le dara al cliente
        List<Libro> list = new ArrayList<>();

        try{
            Optional<Libro> libroBuscado = libroDao.findById(id); // Buscamos el libro por su id

            if(libroBuscado.isPresent()){
                libroBuscado.get().setNombre(libro.getNombre());
                libroBuscado.get().setDescripcion(libro.getDescripcion());
                libroBuscado.get().setCategoria(libro.getCategoria());

                Libro libroActualizado = libroDao.save(libroBuscado.get()); // Actualizamos el libro en la base de datos

                if(libroActualizado!=null) { // Si el libro se actualizo correctamente
                    list.add(libroActualizado); // Agregamos el libro a la lista
                    response.getLibroResponse().setLibros(list); // Guardamos la lista de libros en la respuesta
                }else {
                    log.severe("Error al actualizar el libro");
                    response.setMetadata("Error al actualizar el libro", "500", "Error al actualizar el libro"); // Guardamos los metadatos de la respuesta
                    return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST); // Retornamos la respuesta con el codigo de error 500
                }
            }else{
                log.severe("No se encontro el libro con el id: " + id);
                response.setMetadata("No se encontro el libro", "404", "No se encontro el libro con el id: " + id); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND); // Retornamos la respuesta con el codigo de error 404
            }

        }catch (Exception e){
            log.severe("Error al actualizar el libro: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al actualizar el libro", "500", "Error al actualizar el libro"); // Guardamos los metadatos de la respuesta
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500


        }

        response.setMetadata("Respuesta exitosa", "200", "Libro actualizado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200

    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> eliminar(Long id) {
        log.info("Iniciando metodo eliminar () ");

        LibroResponseRest response = new LibroResponseRest(); // Creamos un objeto de tipo LibroResponseRest para guardar la respuesta que se le dara al cliente

        try{
            Optional<Libro> libroBuscado = libroDao.findById(id); // Buscamos el libro por su id

            if(libroBuscado.isPresent()){
                libroDao.delete(libroBuscado.get()); // Eliminamos el libro de la base de datos
            }else{
                log.severe("No se encontro el libro con el id: " + id);
                response.setMetadata("No se encontro el libro", "404", "No se encontro el libro con el id: " + id); // Guardamos los metadatos de la respuesta
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND); // Retornamos la respuesta con el codigo de error 404
            }


    }catch (Exception e){

        log.severe("Error al eliminar el libro: " + e.getMessage());
        e.getStackTrace();
        response.setMetadata("Error al eliminar el libro", "500", "Error al eliminar el libro"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retornamos la respuesta con el codigo de error 500
    }

        response.setMetadata("Respuesta exitosa", "200", "Libro eliminado"); // Guardamos los metadatos de la respuesta
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // Retornamos la respuesta con el codigo de exito 200
    }
}
