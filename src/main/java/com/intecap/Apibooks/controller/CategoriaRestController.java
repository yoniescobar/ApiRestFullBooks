package com.intecap.Apibooks.controller;

import com.intecap.Apibooks.model.Categoria;
import com.intecap.Apibooks.response.CategoriaResponseRest;
import com.intecap.Apibooks.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin//  (originPatterns = {"http://localhost:3000"}) acceso a datos React
@RestController // Indica que es un controlador REST y que se encarga de recibir las peticiones HTTP y devolver las respuestas en formato JSON
@RequestMapping("api/v1") // uri  Indica la ruta base para todas las peticiones que se hagan a este controlador
public class CategoriaRestController {

    @Autowired // Inyectamos el servicio de categorias para poder utilizarlo en este controlador REST
    private ICategoriaService categoriaService;

    @GetMapping("/categorias") // Indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/categorias
    public ResponseEntity<CategoriaResponseRest> consultarCategorias(){
        return categoriaService.buscarCategorias(); // Invocamos el metodo buscarCategorias del servicio de categorias para obtener las info de la base de datos
    }

    @GetMapping("/categorias/{id}") // Indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/categorias/{id}
    public ResponseEntity<CategoriaResponseRest> consultarCategoriaId(@PathVariable Long id){
        return categoriaService.buscarCategoriaId(id); // Invocamos el metodo buscarCategoriaId del servicio de categorias para obtener la info de la base de datos
    }

    @PostMapping("/categorias") // Indica que este metodo se encarga de recibir las peticiones POST a la ruta /v1/categorias
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request){ // Indica que el objeto Categoria que se recibe en el cuerpo de la peticion se debe convertir a un objeto CategoriaRequestRest
        return categoriaService.crear(request); // Invocamos el metodo crear del servicio de categorias para crear la categoria en la base de datos
    }
    @PutMapping("/categorias/{id}") // Indica que este metodo se encarga de recibir las peticiones PUT a la ruta /v1/categorias/{id}
    public ResponseEntity<CategoriaResponseRest> actualizar(@PathVariable Long id, @RequestBody Categoria request){ // Indica que el objeto Categoria que se recibe en el cuerpo de la peticion se debe convertir a un objeto CategoriaRequestRest
        return categoriaService.actualizar(id,request); // Invocamos el metodo buscarCategoriaId del servicio de categorias para obtener la info de la base de datos
    }

    @DeleteMapping("/categorias/{id}") // Indica que este metodo se encarga de recibir las peticiones DELETE a la ruta /v1/categorias/{id}
    public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id){
        //ResponseEntity: Es un objeto que encapsula la respuesta que se le dara al cliente y que contiene el codigo de estado de la respuesta, el cuerpo de la respuesta y los encabezados de la respuesta
        return categoriaService.eliminar(id); // Retornamos la respuesta al cliente
    }
}
