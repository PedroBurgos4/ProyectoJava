package com.business.libraryLogic;

import com.business.classBase.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class LogicsBooks implements BooksInterface {
    //public class LogicsBooks implements BooksInterface {


    private final EntityManager entityManager;
    public LogicsBooks(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void addBook( Book book){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
            System.out.println("Libro agregado");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Libro no agregado algo salio mal");
            throw e;
        }
    }
    public void deleteBook(Book book){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(book);
            transaction.commit();
            System.out.println("Libro eliminado");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Libro no eliminado algo salio mal");
            throw e;
        }
    }

    public void updateBook(Book book){
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(book);
            transaction.commit();
            System.out.println("Libro actualizado");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Libro no actualizado algo salio mal");
            throw e;
        }
    }
    public Book findBookById(int id){
        return entityManager.find(Book.class, id);
    }
    public List<Book> findAllBooks(){
        String sql = "SELECT * FROM book ";
        return entityManager.createNativeQuery(sql,Book.class).getResultList();

    }
    public List<Book> findBooksByAuthor(String author){
        String sql = "SELECT * FROM book WHERE author = ?";
        return entityManager.createNativeQuery(sql,Book.class).setParameter(1, author).getResultList();
    }

    public List<Book> findBooksByTitle(String title){
        String sql="SELECT * FROM book  WHERE title = ?";
        return entityManager.createNativeQuery(sql, Book.class)
                .setParameter(1, title)
                .getResultList();
    }
    public List<Book> findBooksByAuthorAndTitle(String author, String title){
        String sql="SELECT * FROM book  WHERE author = ? AND title = ?";
        return entityManager.createNativeQuery(sql, Book.class)
                .setParameter(1, author)
                .setParameter(2, title)
                .getResultList();
    }
    public List<Book> findBooksByAuthorAndTitleAndPublicationYear(String author, String title, int publicationYear){
        String sql="SELECT * FROM book  WHERE author = ? AND title = ? AND publicationYear = ?";
        return entityManager.createNativeQuery(sql, Book.class)
                .setParameter(1, author)
                .setParameter(2, title)
                .setParameter(3, publicationYear)
                .getResultList();
    }
    public List<Book> findBooksByAuthorAndTitleAndPublicationYearAndIsbn(String author, String title, int publicationYear, String isbn){
        String sql="SELECT * FROM book  WHERE author = ? AND title = ? AND publicationYear = ? AND isbn = ?";
        return entityManager.createNativeQuery(sql, Book.class)
                .setParameter(1, author)
                .setParameter(2, title)
                .setParameter(3, publicationYear)
                .setParameter(4, isbn)
                .getResultList();
    }
    public void updateById(int id,Book book){
        Book book1 = findBookById(id);
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setPublicationYear(book.getPublicationYear());
        book1.setIsbn(book.getIsbn());
        updateBook(book1);
    }

    public void updateByAuthor(String title, String newAuthor){
        List<Book> books = findBooksByTitle(title);
        if (books.size()>0&&books.size()==1){
            Book book1 = books.get(0);
            book1.setAuthor(newAuthor);
            updateBook(book1);
        }
        if (books.size()>1){
            System.out.println("Hay mas de un libro con el mismo autor");
        }
        if (books.size()==0){
            System.out.println("No se encontro el libro");
        }
    }
    public void updateByTitle(String title, String newTitle){
        List<Book> books = findBooksByTitle(title);
        if (books.size()>0&&books.size()==1){
            Book book1 = books.get(0);
            book1.setTitle(newTitle);
            updateBook(book1);
        }
        if (books.size()>1){
            System.out.println("Hay mas de un libro con el mismo titulo");
        }
        if (books.size()==0){
            System.out.println("No se encontro el libro");
        }
    }
    public void updateByTitlePublicationYear(String title, int newPublicationYear){
        List<Book> books = findBooksByTitle(title);
        if (books.size()>0&&books.size()==1){
            Book book1 = books.get(0);
            book1.setPublicationYear(newPublicationYear);
            updateBook(book1);
        }
        if (books.size()>1){
            System.out.println("Hay mas de un libro con el mismo titulo");
        }
        if (books.size()==0){
            System.out.println("No se encontro el libro");
        }
    }
    public void updateByTitleISBN(String title, String newISBN){
        List<Book> books = findBooksByTitle(title);
        if (books.size()>0&&books.size()==1){
            Book book1 = books.get(0);
            book1.setIsbn(newISBN);
            updateBook(book1);
        }
        if (books.size()>1){
            System.out.println("Hay mas de un libro con el mismo titulo");
        }
        if (books.size()==0){
            System.out.println("No se encontro el libro");
        }
    }
    public void updateByTitleAndAuthor(String title, String author, String newTitle, String newAuthor){
        List<Book> books = findBooksByAuthorAndTitle( author,title);
        if (books.size()>0&&books.size()==1){
            Book book1 = books.get(0);
            book1.setTitle(newTitle);
            book1.setAuthor(newAuthor);
            updateBook(book1);
        }
        if (books.size()>1){
            System.out.println("Hay mas de un libro con el mismo titulo y autor");
        }
        if (books.size()==0){
            System.out.println("No se encontro el libro");
        }
    }




}
