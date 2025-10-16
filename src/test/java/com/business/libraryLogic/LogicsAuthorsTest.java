package com.business.libraryLogic;

import com.business.classBase.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LogicsAuthorsTest {
    private LogicsAuthors logicsAuthors;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("coneccionLocalMySQL");
        entityManager = entityManagerFactory.createEntityManager();
        logicsAuthors = new LogicsAuthors(entityManager);
    }

    @Test
    void addAuthor() {
        Author author = new Author();
        author.setName("Juan");
        author.setLastName("Sanchez");
        author.setBiography("Juan Sanchez es un autor de libros");
        logicsAuthors.addAuthor(author);
        Author author1 = logicsAuthors.findAuthorById(5);
        assertEquals(author1.getName(), "Juan");
    }

    @Test
    void updateAuthor() {
        Author author = logicsAuthors.findAuthorById(5);
        author.setName("Juan");
        author.setLastName("Lopez");
        author.setBiography("Juan Lopez es un autor de libros");
        logicsAuthors.updateAuthor(author);
        Author author1 = logicsAuthors.findAuthorById(5);
        assertEquals(author1.getName(), "Juan");
        assertEquals(author1.getLastName(), "Lopez");
        assertEquals(author1.getBiography(), "Juan Lopez es un autor de libros");
    }

    @Test
    void deleteAuthor() {
        Author author = logicsAuthors.findAuthorById(1);
        logicsAuthors.deleteAuthor(author);
        Author author1 = logicsAuthors.findAuthorById(1);
        assertEquals(author1, null);
    }

    @Test
    void findAuthorById() {
        Author author = logicsAuthors.findAuthorById(5);
        assertEquals(author.getName(), "Juan");
    }

    @Test
    void findAllAuthors() {
        List<Author> authors = logicsAuthors.findAllAuthors();
        assertEquals(authors.size(), 4);
    }

    @Test
    void findAuthorsByLastName() {
        List<Author> authors = logicsAuthors.findAuthorsByLastName("Lopez");
        assertEquals(authors.size(), 1);
    }

    @Test
    void findAuthorsByFirstName() {
        List<Author> authors = logicsAuthors.findAuthorsByFirstName("Juan");
        assertEquals(authors.size(), 1);
        assertEquals(authors.get(0).getName(), "Juan");
    }

    @Test
    void findAuthorsByFirstNameAndLastName() {
        List<Author> authors = logicsAuthors.findAuthorsByFirstNameAndLastName("Juan", "Lopez");
        assertEquals(authors.get(0).getName(), "Juan");
        assertEquals(authors.get(0).getLastName(), "Lopez");
    }

    @Test
    void updateById() {
        Author author = logicsAuthors.findAuthorById(5);
        author.setName("Juan2");
        author.setLastName("Lopez2");
        author.setBiography("Juan Lopez es un autor de libros");
        logicsAuthors.updateById(5, author);
        Author author1 = logicsAuthors.findAuthorById(5);
        assertEquals(author1.getName(), "Juan2");
        assertEquals(author1.getLastName(), "Lopez2");
        assertEquals(author1.getBiography(), "Juan Lopez es un autor de libros");
    }

    @Test
    void updateByFirstName() {
        logicsAuthors.updateByFirstName("Juan2","Juan");
        Author author = logicsAuthors.findAuthorById(5);
        assertEquals(author.getName(), "Juan");
    }

    @Test
    void updateByLastName() {
        logicsAuthors.updateByLastName("Lopez2","Lopez");
        Author author = logicsAuthors.findAuthorById(5);
        assertEquals(author.getLastName(), "Lopez");
    }

    @Test
    void updateByFirstNameAndLastName() {
        logicsAuthors.updateByFirstNameAndLastName("Juan","Lopez","Jorge","Martin");
        Author author = logicsAuthors.findAuthorById(5);
        assertEquals(author.getName(), "Jorge");
        assertEquals(author.getLastName(), "Martin");
    }

    @Test
    void updateByBiography() {
        logicsAuthors.updateByBiography("Jorge", "Jorge Martin es un autor de libros");
        Author author = logicsAuthors.findAuthorById(5);
        assertEquals(author.getBiography(), "Jorge Martin es un autor de libros");
    }
}