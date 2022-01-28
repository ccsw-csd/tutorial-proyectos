package com.capgemini.ccsw.tutorial.author.model;

/**
* @author ccsw
*/
public class AuthorDto {

   private Long id;

   private String name;

   private String nationality;

   /**
   * @return id
   */
   public Long getId() {

      return this.id;
   }

   /**
   * @param id new value of {@link #getid}.
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
   * @param name new value of {@link #getname}.
   */
   public void setName(String name) {

      this.name = name;
   }

   /**
   * @return nationality
   */
   public String getNationality() {

      return this.nationality;
   }

   /**
   * @param nationality new value of {@link #getnationality}.
   */
   public void setNationality(String nationality) {

      this.nationality = nationality;
   }

}