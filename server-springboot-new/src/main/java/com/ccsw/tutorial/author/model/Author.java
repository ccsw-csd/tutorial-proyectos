package com.ccsw.tutorial.author.model;

import jakarta.persistence.*;

/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nationality")
    private String nationality;

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

    /**
     * @return nationality
     */
    public String getNationality() {

        return this.nationality;
    }

    /**
     * @param nationality new value of {@link #getNationality}.
     */
    public void setNationality(String nationality) {

        this.nationality = nationality;
    }

}