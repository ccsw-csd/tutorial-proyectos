package com.capgemini.ccsw.tutorial.config.mapper;

import org.springframework.data.domain.Page;

/**
* @author coedevon
*/
public interface BeanMapper extends com.devonfw.module.beanmapping.common.api.BeanMapper {

   /**
   * Mapea el genérico de un Page en otro tipo de genérico
   * @param <T>
   * @param source
   * @param targetClass
   * @return
   */
   <T> Page<T> mapPage(Page<?> source, Class<T> targetClass);

}