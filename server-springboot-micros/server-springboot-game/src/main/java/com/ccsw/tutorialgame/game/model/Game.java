package com.ccsw.tutorialgame.game.model;

import jakarta.persistence.*;


/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "category_id", nullable = false)
    private Long idCategory;

    @Column(name = "author_id", nullable = false)
    private Long idAuthor;

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
     * @return idCategory
     */
    public Long getIdCategory() {

        return this.idCategory;
    }

    /**
     * @param idCategory new value of {@link #getIdCategory}.
     */
    public void setIdCategory(Long idCategory) {

        this.idCategory = idCategory;
    }

    /**
     * @return idAuthor
     */
    public Long getIdAuthor() {

        return this.idAuthor;
    }

    /**
     * @param idAuthor new value of {@link #getIdAuthor}.
     */
    public void setIdAuthor(Long idAuthor) {

        this.idAuthor = idAuthor;
    }

}