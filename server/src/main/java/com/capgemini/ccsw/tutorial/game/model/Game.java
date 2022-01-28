package com.capgemini.ccsw.tutorial.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.ccsw.tutorial.author.model.Author;
import com.capgemini.ccsw.tutorial.category.model.Category;

/**
* @author ccsw
*/
@Entity
@Table(name = "Game")
public class Game {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "title", nullable = false)
   private String title;

   @Column(name = "age", nullable = false)
   private String age;

   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false)
   private Category category;

   @ManyToOne
   @JoinColumn(name = "author_id", nullable = false)
   private Author author;

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
   * @return title
   */
   public String getTitle() {

      return this.title;
   }

   /**
   * @param title new value of {@link #getTitle}.
   */
   public void setTitle(String title) {

      this.title = title;
   }

   /**
   * @return age
   */
   public String getAge() {

      return this.age;
   }

   /**
   * @param age new value of {@link #getAge}.
   */
   public void setAge(String age) {

      this.age = age;
   }

   /**
   * @return category
   */
   public Category getCategory() {

      return this.category;
   }

   /**
   * @param category new value of {@link #getCategory}.
   */
   public void setCategory(Category category) {

      this.category = category;
   }

   /**
   * @return author
   */
   public Author getAuthor() {

      return this.author;
   }

   /**
   * @param author new value of {@link #getAuthor}.
   */
   public void setAuthor(Author author) {

      this.author = author;
   }

}