package com.business.libraryLogic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.business.classBase.Book;
import com.business.libraryLogic.LogicsBooks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogicsBooksTest {
    private LogicsBooks logicsBooks;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("coneccionLocalMySQL");
        entityManager = entityManagerFactory.createEntityManager();
        logicsBooks = new LogicsBooks(entityManager);
    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setTitle("El Quijote3");
        book.setAuthor("Miguel de Cervantes3");
        book.setIsbn("123456789333");
        book.setPublicationYear(16053);
        logicsBooks.addBook(book);
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getTitle(), "El Quijote3");

    }

    @Test
    void deleteBook() {
        Book book = logicsBooks.findBookById(1);
        logicsBooks.deleteBook(book);
        Book book1 = logicsBooks.findBookById(1);
        assertEquals(book1, null);
    }

    @Test
    void updateBook() {
        Book book = logicsBooks.findBookById(5);
        book.setTitle("El Quijote4");
        logicsBooks.updateBook(book);
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getTitle(), "El Quijote4");
    }

    @Test
    void findBookById() {
        Book book = logicsBooks.findBookById(5);
        assertEquals(book.getTitle(), "El Quijote4");
    }

    @Test
    void findAllBooks() {
        List<Book> books = logicsBooks.findAllBooks();
        assertEquals(books.size(), 5);
    }

    @Test
    void findBooksByAuthor() {
        List<Book> books = logicsBooks.findBooksByAuthor("Miguel de Cervantes3");
        assertEquals(books.size(), 1);
    }

    @Test
    void findBooksByTitle() {
        List<Book> books = logicsBooks.findBooksByTitle("El Quijote4");
        assertEquals(books.get(0).getTitle(), "El Quijote4");
    }

    @Test
    void findBooksByAuthorAndTitle() {
        List<Book> books = logicsBooks.findBooksByAuthorAndTitle("Miguel de Cervantes3", "El Quijote4");
        assertEquals(books.get(0).getTitle(), "El Quijote4");
        assertEquals(books.get(0).getAuthor(), "Miguel de Cervantes3");
    }

    @Test
    void findBooksByAuthorAndTitleAndPublicationYear() {
        List<Book> books = logicsBooks.findBooksByAuthorAndTitleAndPublicationYear("Miguel de Cervantes3", "El Quijote4", 16053);
        assertEquals(books.get(0).getTitle(), "El Quijote4");
        assertEquals(books.get(0).getAuthor(), "Miguel de Cervantes3");
        assertEquals(books.get(0).getPublicationYear(), 16053);
    }

    @Test
    void findBooksByAuthorAndTitleAndPublicationYearAndIsbn() {
        List<Book> books = logicsBooks.findBooksByAuthorAndTitleAndPublicationYearAndIsbn("Miguel de Cervantes3", "El Quijote4", 16053, "123456789333");
        assertEquals(books.get(0).getTitle(), "El Quijote4");
        assertEquals(books.get(0).getAuthor(), "Miguel de Cervantes3");
        assertEquals(books.get(0).getPublicationYear(), 16053);
        assertEquals(books.get(0).getIsbn(), "123456789333");
    }

    @Test
    void updateById() {
        Book newBook = new Book();
        newBook.setTitle("El Quijote 2");
        newBook.setAuthor("Miguel de Cervantes");
        newBook.setIsbn("123456789");
        newBook.setPublicationYear(1605);
        logicsBooks.updateById(5, newBook);
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getTitle(), "El Quijote 2");
    }

    @Test
    void updateByAuthor() {
        logicsBooks.updateByAuthor("El Quijote 2", "Miguel de Cervantes6");
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getAuthor(), "Miguel de Cervantes6");
    }

    @Test
    void updateByTitle() {
        logicsBooks.updateByTitle("El Quijote 2", "El Quijote 6");
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getTitle(), "El Quijote 6");
    }

    @Test
    void updateByTitlePublicationYear() {
        logicsBooks.updateByTitlePublicationYear("El Quijote 6", 1606);
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getPublicationYear(), 1606);

    }

    @Test
    void updateByTitleISBN() {
        logicsBooks.updateByTitleISBN("El Quijote 6", "9876543210");
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getIsbn(), "9876543210");
    }

    @Test
    void updateByTitleAndAuthor() {
        logicsBooks.updateByTitleAndAuthor("El Quijote 6", "Miguel de Cervantes6", "El Quijote 7", "Miguel de Cervantes7");
        Book book1 = logicsBooks.findBookById(5);
        assertEquals(book1.getTitle(), "El Quijote 7");
        assertEquals(book1.getAuthor(), "Miguel de Cervantes7");
    }




}