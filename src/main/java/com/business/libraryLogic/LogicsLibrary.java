package com.business.libraryLogic;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LogicsLibrary {
    private final EntityManager entityManager;
    public LogicsLibrary(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    public List<Object> findAuthorBooks(String author){
        String sql="SELECT * FROM author, book WHERE author.name =? AND book.author=?";
        return entityManager.createNativeQuery(sql)
                .setParameter(1, author)
                .setParameter(2, author)
                .getResultList();
    }

}
