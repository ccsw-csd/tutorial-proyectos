package com.capgemini.ccsw.tutorial.category.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @author ccsw
*/
@Entity
@Table(name = "Category")
public class Category {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "name", nullable = false)
   private String name;

   /**
   * @return id
   */
   public Long getId() {

      return this.id;
   }

   /**
   * @param id new value of {@link #getId}.
   */
   public void setId(Long id) {

      this.id = id;
   }

   /**
   * @return name
   */
   public String getName() {

      return this.name;
   }

   /**
   * @param name new value of {@link #getName}.
   */
   public void setName(String name) {

      this.name = name;
   }

}