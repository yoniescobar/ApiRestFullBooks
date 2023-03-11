package com.intecap.Apibooks.service;

import com.intecap.Apibooks.model.Categoria;
import com.intecap.Apibooks.model.dao.ICategoriaDao;
import com.intecap.Apibooks.response.CategoriaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service //Indica que es una clase de servicio y que se debe inyectar en el controlador para poder acceder a los datos de la tabla categoria de la base de datos
public class CategoriaServiceImpl implements ICategoriaService {


    private static final Logger log = Logger.getLogger(CategoriaServiceImpl.class.getName());

    @Autowired //Inyeccion de dependencias
    private ICategoriaDao categoriaDao; //Objeto de la interface ICategoriaDao para acceder a los datos de la tabla categoria de la base de datos

    @Override //Sobreescribe el metodo buscarCategorias de la interface ICategoriaService
    @Transactional(readOnly = true) //Indica que es una transaccion de solo lectura y que no se debe hacer commit en la base de datos al finalizar la transaccion (no se debe guardar los datos en la base de datos)
    public CategoriaResponseRest buscarCategorias() {
        log.info("Inicio metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest(); // hace referencia a la clase CategoriaResponseRest que se encuentra en el paquete response y que es la clase que se encarga de enviar la respuesta al cliente

        try{
            List<Categoria> listCategoria = (List<Categoria>) categoriaDao.findAll() ; //llama al metodo findAll de la interface ICategoriaDao para buscar todas las categorias de la base de datos
            response.getCategoriaResponse().setCategorias(listCategoria); //llama al metodo setCategorias de la clase CategoriaResponseRest para enviar la lista de categorias al cliente
            response.setMetadata("Respuesta OK","200", "Lista de categorias"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente


        }catch (Exception e){
            log.info("Error al consultar las categorias " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta nOK ","-1","Repuesta Incorrecta"); //llama al metodo setMetadata de la clase CategoriaResponseRest para enviar el mensaje de respuesta al cliente
            return response;


        }
        return response;
    }
}
