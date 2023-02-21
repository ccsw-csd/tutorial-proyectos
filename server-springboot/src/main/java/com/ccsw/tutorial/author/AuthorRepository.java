package com.ccsw.tutorial.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.author.model.Author;

/**
* @author ccsw
*/
public interface AuthorRepository extends CrudRepository<Author, Long> {

   /**
    * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.author.model.Author}
    * @param page
    * @return
    */
   Page<Author> findAll(Pageable pageable);

}
