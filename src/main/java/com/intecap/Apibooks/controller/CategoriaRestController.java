package com.intecap.Apibooks.controller;

import com.intecap.Apibooks.response.CategoriaResponseRest;
import com.intecap.Apibooks.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que es un controlador REST y que se encarga de recibir las peticiones HTTP y devolver las respuestas en formato JSON
@RequestMapping("api/v1") // uri  Indica la ruta base para todas las peticiones que se hagan a este controlador
public class CategoriaRestController {

    @Autowired // Inyectamos el servicio de categorias para poder utilizarlo en este controlador REST
    private ICategoriaService categoriaService;

    @GetMapping("/categorias") // Indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/categorias
    public CategoriaResponseRest  consultarCategorias(){
        return categoriaService.buscarCategorias(); // Invocamos el metodo buscarCategorias del servicio de categorias para obtener las info de la base de datos
    }

}
