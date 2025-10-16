package com.application;
import com.business.classBase.Author;
import com.business.classBase.Book;
import com.business.classBase.User;
import com.business.libraryLogic.LogicsAuthors;
import com.business.libraryLogic.LogicsBooks;
import com.business.libraryLogic.LogicsUsers;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainClass {
    public static void Application(EntityManager entityManager){
        LogicsBooks logicsBooks = new LogicsBooks(entityManager);
        LogicsAuthors logicsAuthors = new LogicsAuthors(entityManager);
        LogicsUsers logicsUsers = new LogicsUsers(entityManager);
        //creando libros
        System.out.println("----Agregando libros a la libreria------");
        Book book = new Book();
        book.setTitle("El Quijote");
        book.setAuthor("Miguel de Cervantes");
        book.setIsbn("123456789");
        book.setPublicationYear(1605);
        //System.out.println(book);
        logicsBooks.addBook(book);
        Book book1 = new Book();
        book1.setTitle("El principito");
        book1.setAuthor("Antoine de Saint-Exupéry");
        book1.setPublicationYear(1943);
        book1.setIsbn("1234567890");
        //System.out.println(book1);
        logicsBooks.addBook(book1);
        Book book2 = new Book();
        book2.setTitle("Los tres mosqueteros");
        book2.setAuthor("Alexandre Dumas");
        book2.setIsbn("167894532");
        book2.setPublicationYear(1844);
        //System.out.println(book2);
        logicsBooks.addBook(book2);
        Book book3 = new Book();
        book3.setTitle("Movidick");
        book3.setAuthor("Herman Melville");
        book3.setIsbn("322565789");
        book3.setPublicationYear(1851);
        //System.out.println(book3);
        logicsBooks.addBook(book3);
        System.out.println("----Busqueda de libros------");
        List<Book> books = logicsBooks.findAllBooks();
        books.forEach(bookss -> System.out.println(bookss.toString()));
        //System.out.println(books);
        List<Book> booksByTitle = logicsBooks.findBooksByTitle("El Quijote");
        booksByTitle.forEach(booksT -> System.out.println(booksT.toString()));
        //System.out.println(booksByTitle);
        List<Book> booksByAuthor = logicsBooks.findBooksByAuthor("Miguel de Cervantes");
        booksByAuthor.forEach(booksA -> System.out.println(booksA.toString()));
        //System.out.println(booksByAuthor);
        List<Book> booksByAuthorAndTitle=  logicsBooks.findBooksByAuthorAndTitle( "Miguel de Cervantes","El Quijote");
        booksByAuthorAndTitle.forEach(booksAT -> System.out.println(booksAT.toString()));
        //System.out.println(booksByAuthorAndTitle);
        List<Book> booksATY=  logicsBooks.findBooksByAuthorAndTitleAndPublicationYear("Miguel de Cervantes", "El Quijote", 1605);
        booksATY.forEach(booksBATY ->System.out.println( booksBATY.toString()));
        //System.out.println(booksATY);
        //actualizacion de libros
        System.out.println("----Actualizacion de libros------");
        Book newBook = new Book();
        newBook.setTitle("El Quijote 2");
        newBook.setAuthor("Miguel de Cervantes");
        newBook.setIsbn("123456789");
        newBook.setPublicationYear(1605);
        logicsBooks.updateById(1,newBook);
        logicsBooks.updateByTitle("El Quijote 2", "El Quijote");
        logicsBooks.updateByTitlePublicationYear("El Quijote", 1606);
        logicsBooks.updateByTitleISBN("El Quijote", "12345678999");
        logicsBooks.updateByTitleAndAuthor("El Quijote", "Miguel de Cervantes", "El Quijote 2", "Miguel de Cervantes");

        System.out.println("----------Eliminar libros----------");
        Book book5=new Book();
        book5.setTitle("El resplandor");
        book5.setAuthor("Stephen King");
        book5.setIsbn("123456789");
        book5.setPublicationYear(1986);
//        logicsBooks.addBook(book5);
        Book book6=logicsBooks.findBookById(5);
//        logicsBooks.deleteBook(book6);


        //creando autores
        Author author = new Author();
        author.setName("Miguel");
        author.setLastName("de Cervantes");
        author.setBiography("Miguel de Cervantes Saavedra (1547-1616) fue un escritor español, considerado uno de los más importantes de la literatura española y mundial.");
        System.out.println(author);
        logicsAuthors.addAuthor(author);
        Author author2 = new Author();
        author2.setName("Antoine");
        author2.setLastName("de Saint-Exupéry");
        author2.setBiography("Antoine de Saint-Exupéry (1900-1944) fue un escritor francés, considerado uno de los más importantes de la literatura francesa y mundial.");
        System.out.println(author2);
        logicsAuthors.addAuthor(author2);
        Author author3 = new Author();
        author3.setName("Alexandre");
        author3.setLastName("Dumas");
        author3.setBiography("Alexandre Dumas (1802-1870) fue un escritor francés, considerado uno de los más importantes de la literatura francesa y mundial.");
        System.out.println(author3);
        logicsAuthors.addAuthor(author3);
        Author author4 = new Author();
        author4.setName("Herman");
        author4.setLastName("Melville");
        author4.setBiography("Herman Melville (1819-1891) fue un escritor estadounidense, considerado uno de los más importantes de la literatura estadounidense y mundial.");
        System.out.println(author4);
        logicsAuthors.addAuthor(author4);
        System.out.println("-----------Busqueda de autores----------");
        Author authorsById = logicsAuthors.findAuthorById(1);
        System.out.println(authorsById.toString());
        List<Author> authors = logicsAuthors.findAllAuthors();
        authors.forEach(authorss -> System.out.println(authorss.toString()));
        List<Author> authorByName=logicsAuthors.findAuthorsByFirstName("Miguel");
        authorByName.forEach(authorsByName -> System.out.println(authorsByName.toString()));
        List<Author> authorsByLastName = logicsAuthors.findAuthorsByLastName("de Cervantes");
        authorsByLastName.forEach(authorsBL -> System.out.println(authorsBL.toString()));
        List<Author> authorsByFirstNameAndLastName=logicsAuthors.findAuthorsByFirstNameAndLastName("Miguel", "de Cervantes");
        authorsByFirstNameAndLastName.forEach(authorsBFALN -> System.out.println(authorsBFALN.toString()));
        System.out.println("-----------Actualizacion de autores----------");
        Author author5=new Author();
        author5.setName("Miguel");
        author5.setLastName("de Cervantes");
        author5.setBiography("Miguel de Cervantes Saavedra (1547-1616) fue un escritor español.");
        logicsAuthors.updateById(1,author5);
        logicsAuthors.updateByFirstName("Miguel", "de Cervantes");
        logicsAuthors.updateByLastName("de Cervantes", "de Cervantes2");
        logicsAuthors.updateByFirstNameAndLastName("de Cervantes", "de Cervantes2", "Miguel", "de Cervantes");
        logicsAuthors.updateByBiography("Miguel", "Miguel de Cervantes Saavedra (1547-1616) fue un escritor.");
        Author author6=logicsAuthors.findAuthorById(3);
        System.out.println(author6.toString());
        author6.setBiography("Alexandre Dumas (1802-1870) fue un escritor francés.");
        //logicsAuthors.updateAuthor(author6);
        System.out.println("-----------Eliminacion de autores----------");
        Author author7=logicsAuthors.findAuthorById(4);
        //logicsAuthors.deleteAuthor(author7);
        //creando usuarios
        User user = new User();
        user.setName("Pedro");
        user.setEmail("pedro@pedro.com");
        user.setPassword("pedro");
        System.out.println(user);
        logicsUsers.addUser(user);
        User user1=new User();
        user1.setName("Ana");
        user1.setEmail("ana@ana.com");
        user1.setPassword("ana");
        System.out.println(user1);
        logicsUsers.addUser(user1);
        User user2=new User();
        user2.setName("Juan");
        user2.setEmail("juan@juan.com");
        user2.setPassword("juan");
        System.out.println(user2);
        logicsUsers.addUser(user2);
        System.out.println("-----------Busqueda de usuarios----------");
        User usersById = logicsUsers.findUserById(3);
        System.out.println(usersById.toString());
        List<User> users = logicsUsers.findAllUsers();
        users.forEach(userss -> System.out.println(userss.toString()));
        List<User> usersByName=logicsUsers.findByUserName("Pedro");
        usersByName.forEach(usersByNames -> System.out.println(usersByNames.toString()));
        List<User> usersByEmail=logicsUsers.findByUserEmail("ana@ana.com");
        usersByEmail.forEach(usersByEmails -> System.out.println(usersByEmails.toString()));
        List<User> usersByNameAndEmail=logicsUsers.findByUserEmailAndName("juan@juan.com", "Juan");
        usersByNameAndEmail.forEach(usersByNameAndEmails -> System.out.println(usersByNameAndEmails.toString()));
        System.out.println("-----------Actualizacion de usuarios----------");
        User newUser=new User();
        newUser.setName("Pedro");
        newUser.setEmail("pedro@hotmail.com");
        newUser.setPassword("123456789");
        logicsUsers.updateById(1,newUser);
        logicsUsers.updateByUserName("Pedro", "Pedro2");
        logicsUsers.updateByUserEmail("pedro@hotmail.com", "pedro2@pedro.com");
        logicsUsers.updateByUserEmailAndName("pedro2@pedro.com", "Pedro2", "pedro2@pedro.com", "Pedro2");
        logicsUsers.updateByUserPassword("pedro2@pedro.com", "pedro2");


        System.out.println("--------------Eliminacion de usuarios--------------");
        //logicsUsers.deleteUser(user4);









    }

}
