package com.intecap.Apibooks.controller;

import com.intecap.Apibooks.response.LibroResponseRest;
import com.intecap.Apibooks.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @RestController: indica que es un controlador de tipo REST
@RequestMapping("/api/v1") // @RequestMapping: indica la ruta base de la API
public class LibroRestController {

    @Autowired // @Autowired: inyecta el servicio de libros para poder utilizarlo en este controlador REST
    private ILibroService libroService;


    @GetMapping("/libros") // @GetMapping: indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/libros
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        return libroService.buscarLibros(); // Retornamos la respuesta al cliente
    }

}
