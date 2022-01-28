package com.capgemini.ccsw.tutorial.category;

import java.util.List;

import com.capgemini.ccsw.tutorial.category.model.Category;
import com.capgemini.ccsw.tutorial.category.model.CategoryDto;

/**
* @author ccsw
*
*/
public interface CategoryService {

   /**
    * Recupera una {@link com.capgemini.ccsw.tutorial.category.model.Category} a partir de su ID
    * @param id
    * @return
    */
   Category get(Long id);

   /**
   * Método para recuperar todas las {@link com.capgemini.ccsw.tutorial.category.model.Category}
   * @return
   */
   List<Category> findAll();

   /**
   * Método para crear o actualizar una {@link com.capgemini.ccsw.tutorial.category.model.Category}
   * @param dto
   * @return
   */
   void save(Long id, CategoryDto dto);

   /**
   * Método para borrar una {@link com.capgemini.ccsw.tutorial.category.model.Category}
   * @param id
   */
   void delete(Long id);
}