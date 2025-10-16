package com.business.classBase;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAuthor;
    @Column(name="name")
    private String name;
    @Column(name="lastName")
    private String lastName;
    @Column(name="biography")
    private String biography;

    @Override
    public String toString() {
        return "Author nombre: " + name + ", apellido: " + lastName + ", biografia: " + biography + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public Object getAuthor() {
        return this;
    }
}
