package com.business.classBase;
import jakarta.persistence.*;
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;
    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="publicationYear")
    private int publicationYear;
    @Column(name="isbn")
    private String isbn;
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public String getIsbn() {
        return isbn;
    }
    @Override
    public String toString() {
        return "Book [id=" + idBook + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", isbn=" + isbn + "]";
    }

}
