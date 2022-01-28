package com.capgemini.ccsw.tutorial.category;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.ccsw.tutorial.category.model.Category;

/**
* @author ccsw
*/
public interface CategoryRepository extends CrudRepository<Category, Long> {

}