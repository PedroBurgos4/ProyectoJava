package com.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("coneccionLocalMySQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        MainClass app = new MainClass();
        app.Application(entityManager);
        entityManager.close();
        entityManagerFactory.close();
    }
}
