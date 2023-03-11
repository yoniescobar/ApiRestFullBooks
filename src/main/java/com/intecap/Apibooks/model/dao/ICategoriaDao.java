package com.intecap.Apibooks.model.dao;

import com.intecap.Apibooks.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
// Interface: es un contrato que debe cumplir la clase que la implementa (hereda) y que define los metodos que debe tener la clase que la implementa (hereda)
//Dao: Data Access Object (Objeto de acceso a datos) es un patron de diseño que permite separar la logica de negocio de la logica de acceso a datos (persistencia de datos)

}
