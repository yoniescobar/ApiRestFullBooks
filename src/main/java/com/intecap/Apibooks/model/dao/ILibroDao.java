package com.intecap.Apibooks.model.dao;

import com.intecap.Apibooks.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro, Long> {


}
