package com.intecap.Apibooks.service;

import com.intecap.Apibooks.model.Categoria;
import com.intecap.Apibooks.model.dao.ICategoriaDao;
import com.intecap.Apibooks.response.CategoriaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
//Indica que es una clase de servicio y que se debe inyectar en el controlador para poder acceder a los datos de la tabla categoria de la base de datos
public class CategoriaServiceImpl implements ICategoriaService {


    private static final Logger log = Logger.getLogger(CategoriaServiceImpl.class.getName());

    @Autowired //Inyeccion de dependencias
    private ICategoriaDao categoriaDao; //Objeto de la interface ICategoriaDao para acceder a los datos de la tabla categoria de la base de datos

    @Override //Sobreescribe el metodo buscarCategorias de la interface ICategoriaService
    @Transactional(readOnly = true)
    //Indica que es una transaccion de solo lectura y que no se debe hacer commit en la base de datos al finalizar la transaccion (no se debe guardar los datos en la base de datos)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        log.info("Inicio metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest(); // hace referencia a la clase CategoriaResponseRest que se encuentra en el paquete response y que es la clase que se encarga de enviar la respuesta al cliente

        try {
            List<Categoria> listCategoria = (List<Categoria>) categoriaDao.findAll(); //llama al metodo findAll de la interface ICategoriaDao para buscar todas las categorias de la base de datos
            response.getCategoriaResponse().setCategorias(listCategoria); //llama al metodo setCategorias de la clase CategoriaResponseRest para enviar la lista de categorias al cliente
            response.setMetadata("Respuesta OK", "200", "Lista de categorias"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente


        } catch (Exception e) {
            log.info("Error al consultar las categorias " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Repuesta Incorrecta"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500

        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// codigo 200
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaId(Long id) {
        log.info("Inicio metodo buscarCategoriaId()");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id); //llama al metodo findById de la interface ICategoriaDao para buscar una categoria por su id

            if (categoria.isPresent()) {
                list.add(categoria.get());
                response.getCategoriaResponse().setCategorias(list);
                response.setMetadata("Respuesta OK", "200", "Categoria encontrada"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Categoria no encontrada"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }

        } catch (Exception e) {
            log.info("Error al consultar la categoria " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al consultar la Categoria"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }


        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// codigo 200

    }

    @Override
    @Transactional
    // indica que es una transaccion de escritura y que se debe hacer commit en la base de datos al finalizar la transaccion (se debe guardar los datos en la base de datos)
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
        log.info("Inicio metodo crear()");

        CategoriaResponseRest response = new CategoriaResponseRest(); // hace referencia a la clase CategoriaResponseRest que se encuentra en el paquete response y que es la clase que se encarga de enviar la respuesta al cliente
        List<Categoria> list = new ArrayList<>();

        try {
            Categoria categoriaGuardada = categoriaDao.save(categoria); //llama al metodo save de la interface ICategoriaDao para guardar una categoria en la base de datos

            if (categoriaGuardada != null) { //si la categoria se guardo correctamente
                list.add(categoriaGuardada);
                response.getCategoriaResponse().setCategorias(list);
                response.setMetadata("Respuesta OK", "200", "Categoria creada"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Error al crear la Categoria"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
            }
        } catch (Exception e) {
            log.info("Error al crear la categoria " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al crear la Categoria"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.CREATED); // codigo 201
    }

    @Override
    @Transactional
    // indica que es una transaccion de escritura y que se debe hacer commit en la base de datos al finalizar la transaccion (se debe guardar los datos en la base de datos)
    public ResponseEntity<CategoriaResponseRest> actualizar(Long id, Categoria categoria) {
        log.info("Inicio metodo actualizar()");
        CategoriaResponseRest response = new CategoriaResponseRest(); // hace referencia a la clase CategoriaResponseRest que se encuentra en el paquete response y que es la clase que se encarga de enviar la respuesta al cliente
        List<Categoria> list = new ArrayList<>();

        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id); //llama al metodo findById de la interface ICategoriaDao para buscar una categoria por su id

            if (categoriaBuscada.isPresent()) {
                categoriaBuscada.get().setNombre(categoria.getNombre());
                categoriaBuscada.get().setDescripcion(categoria.getDescripcion()); //llama al metodo setDescripcion de la clase Categoria para actualizar la descripcion de la categoria

                Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get()); //llama al metodo save de la interface ICategoriaDao para actualizar una categoria en la base de datos

                if (categoriaActualizada != null) { //si la categoria se actualizo correctamente
                    list.add(categoriaActualizada);
                    response.getCategoriaResponse().setCategorias(list);
                    response.setMetadata("Respuesta OK", "200", "Categoria actualizada");

                } else {
                    response.setMetadata("Respuesta nOK ", "-1", "Error al actualizar la Categoria");
                    return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST); // error 400
                }
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.info("Error al actualizar la categoria " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al actualizar la Categoria");
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // codigo 200

    }

    @Override
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar()");
        CategoriaResponseRest response = new CategoriaResponseRest(); // hace referencia a la clase CategoriaResponseRest que se encuentra en el paquete response y que es la clase que se encarga de enviar la respuesta al cliente
        List<Categoria> list = new ArrayList<>();

        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id); //llama al metodo findById de la interface ICategoriaDao para buscar una categoria por su id

            if (categoriaBuscada.isPresent()) { //si la categoria se encontro
                categoriaDao.delete(categoriaBuscada.get()); //llama al metodo delete de la interface ICategoriaDao para eliminar una categoria en la base de datos
                list.add(categoriaBuscada.get());
                response.getCategoriaResponse().setCategorias(list);
                response.setMetadata("Respuesta OK", "200", "Categoria eliminada"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            } else {
                response.setMetadata("Respuesta nOK ", "-1", "Categoria no encontrada"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND); // error 404
            }
        } catch (Exception e) {
            log.info("Error al eliminar la categoria " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ", "-1", "Error al eliminar la Categoria"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // codigo 200

    }
}
