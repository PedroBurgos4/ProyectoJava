package com.business.libraryLogic;

import com.business.classBase.Book;

import java.util.List;

public interface BooksInterface {
    public void addBook(Book book);
    public void updateBook(Book book);
    public void deleteBook(Book book);
    public Book findBookById(int id);
    public List<Book> findAllBooks();
    public List<Book> findBooksByTitle(String title);
    public List<Book> findBooksByAuthor(String author);


}
