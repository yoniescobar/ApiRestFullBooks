package com.intecap.Apibooks.controller;

import com.intecap.Apibooks.model.Libro;
import com.intecap.Apibooks.response.LibroResponseRest;
import com.intecap.Apibooks.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @RestController: indica que es un controlador de tipo REST
@RequestMapping("/api/v1") // @RequestMapping: indica la ruta base de la API
@CrossOrigin(origins = "4000") // @CrossOrigin: indica que esta API puede ser consumida por cualquier cliente
public class LibroRestController {

    @Autowired // @Autowired: inyecta el servicio de libros para poder utilizarlo en este controlador REST
    private ILibroService libroService;


    @GetMapping("/libros") // @GetMapping: indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/libros
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        return libroService.buscarLibros(); // Retornamos la respuesta al cliente
    }

    @GetMapping("/libros/{id}") // @GetMapping: indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/libros/{id}
    public ResponseEntity<LibroResponseRest> consultarPorId(@PathVariable Long id) {
        return libroService.buscarLibroPorId(id);
    }

    @PostMapping("/libros") // @PostMapping: indica que este metodo se encarga de recibir las peticiones POST a la ruta /v1/libros
    public ResponseEntity<LibroResponseRest> guardarLibro(@RequestBody Libro request) {
        return libroService.crear(request);
    }

    @PutMapping("/libros/{id}") // @PutMapping: indica que este metodo se encarga de recibir las peticiones PUT a la ruta /v1/libros
    public ResponseEntity<LibroResponseRest> actualizarLibro(@RequestBody Libro request, @PathVariable Long id) {
        return libroService.actualizar(request,id);

    }

    @DeleteMapping("/libros/{id}") // @DeleteMapping: indica que este metodo se encarga de recibir las peticiones DELETE a la ruta /v1/libros/{id}
    public ResponseEntity<LibroResponseRest> eliminarLibro(@PathVariable Long id) {
        return libroService.eliminar(id);
    }

}
