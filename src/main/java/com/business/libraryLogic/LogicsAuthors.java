package com.business.libraryLogic;

import com.business.classBase.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class LogicsAuthors {
    private final EntityManager entityManager;
    public LogicsAuthors(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void addAuthor(Author author){
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
            System.out.println("Autor agregado");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Autor no agregado algo salio mal");
            throw e;
        }
    }
    public void updateAuthor(Author author){
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(author);
            transaction.commit();
            System.out.println("Autor actualizado");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Autor no actualizado algo salio mal");
            throw e;
        }
    }
    public void deleteAuthor(Author author){
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(author);
            transaction.commit();
            System.out.println("Autor eliminado");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Autor no eliminado algo salio mal");
            throw e;
        }
    }
    public Author findAuthorById(int id){
        return entityManager.find(Author.class,id );
    }
    public List<Author> findAllAuthors(){
        String sql="SELECT * FROM author";
        return entityManager.createNativeQuery(sql, Author.class).getResultList();
    }
    public List<Author> findAuthorsByLastName(String lastName){
        String sql="SELECT * FROM author WHERE lastName = ?";
        return entityManager.createNativeQuery(sql, Author.class).setParameter(1, lastName).getResultList();
    }
    public List<Author> findAuthorsByFirstName(String firstName){
        String sql="SELECT * FROM author WHERE name = ?";
        return entityManager.createNativeQuery(sql, Author.class).setParameter(1, firstName).getResultList();
    }
    public List<Author> findAuthorsByFirstNameAndLastName(String firstName, String lastName){
        String sql="SELECT * FROM author WHERE name = ? AND lastName = ?";
        return entityManager.createNativeQuery(sql, Author.class).setParameter(1, firstName).setParameter(2, lastName).getResultList();
    }
    public void updateById(int id,Author author){
        Author author1 = findAuthorById(id);
        author1.setName(author.getName());
        author1.setLastName(author.getLastName());
        author1.setBiography(author.getBiography());
        updateAuthor(author1);
    }
    public void updateByFirstName(String firstName, String newFirstName){
        List<Author> authors = findAuthorsByFirstName(firstName);
        if (authors.size()>0&&authors.size()==1){
            Author author1 = authors.get(0);
            author1.setName(newFirstName);
            updateAuthor(author1);
        }
        if (authors.size()>1){
            System.out.println("Hay mas de un autor con el mismo nombre");
        }
        if (authors.size()==0){
            System.out.println("No se encontro el autor");
        }
    }
    public void updateByLastName(String lastName, String newLastName){
        List<Author> authors = findAuthorsByLastName(lastName);
        if (authors.size()>0&&authors.size()==1){
            Author author1 = authors.get(0);
            author1.setLastName(newLastName);
            updateAuthor(author1);
        }
        if (authors.size()>1){
            System.out.println("Hay mas de un autor con el mismo apellido");
        }
        if (authors.size()==0){
            System.out.println("No se encontro el autor");
        }
    }
    public void updateByFirstNameAndLastName(String firstName, String lastName, String newFirstName, String newLastName){
        List<Author> authors = findAuthorsByFirstNameAndLastName(firstName, lastName);
        if (authors.size()>0&&authors.size()==1){
            Author author1 = authors.get(0);
            author1.setName(newFirstName);
            author1.setLastName(newLastName);
            updateAuthor(author1);
        }
        if (authors.size()>1){
            System.out.println("Hay mas de un autor con el mismo nombre y apellido");
        }
        if (authors.size()==0){
            System.out.println("No se encontro el autor");
        }
    }
    public void updateByBiography(String name, String newBiography){
        List<Author> authors = findAuthorsByFirstName(name);
        if (authors.size()>0&&authors.size()==1){
            Author author1 = authors.get(0);
            author1.setBiography(newBiography);
            updateAuthor(author1);
        }
        if (authors.size()>1){
            System.out.println("Hay mas de un autor con el mismo nombre");
        }
        if (authors.size()==0){
            System.out.println("No se encontro el autor");
        }
    }
}
