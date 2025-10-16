package com.business.libraryLogic;

import com.business.classBase.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LogicsUsersTest {
    private LogicsUsers logicsUsers;
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("coneccionLocalMySQL");
        entityManager = entityManagerFactory.createEntityManager();
        logicsUsers = new LogicsUsers(entityManager);
    }

    @Test
    void addUser() {
        User user = new User();
        user.setName("Maria");
        user.setEmail("maria@ebac.com");
        user.setPassword("123456");
        logicsUsers.addUser(user);
        User user1 = logicsUsers.findUserById(4);
        assertEquals(user1.getName(), "Maria");
        assertEquals(user1.getEmail(), "maria@ebac.com");
        assertEquals(user1.getPassword(), "123456");
    }

    @Test
    void deleteUser() {
        User user = logicsUsers.findUserById(1);
        logicsUsers.deleteUser(user);
        User user1 = logicsUsers.findUserById(1);
        assertEquals(user1, null);

    }

    @Test
    void updateUser() {
        User user = logicsUsers.findUserById(4);
        user.setName("Pedro");
        user.setEmail("pedro@ebac.com");
        user.setPassword("654432");
        logicsUsers.updateUser(user);
        User user1 = logicsUsers.findUserById(4);
        assertEquals(user1.getName(), "Pedro");
        assertEquals(user1.getEmail(), "pedro@ebac.com");
        assertEquals(user1.getPassword(), "654432");
    }

    @Test
    void findUserById() {
        User user = logicsUsers.findUserById(4);
        assertEquals(user.getName(), "Pedro");
        assertEquals(user.getEmail(), "pedro@ebac.com");
        assertEquals(user.getPassword(), "654432");

    }

    @Test
    void findAllUsers() {
        List<User> users = logicsUsers.findAllUsers();
        assertEquals(users.size(), 3);
    }

    @Test
    void findByUserName() {
        List<User> users = logicsUsers.findByUserName("Pedro");
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getName(), "Pedro");
    }

    @Test
    void findByUserEmail() {
        List<User> users = logicsUsers.findByUserEmail("pedro@ebac.com");
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getEmail(), "pedro@ebac.com");
    }

    @Test
    void findByUserEmailAndName() {
        List<User> users = logicsUsers.findByUserEmailAndName("pedro@ebac.com", "Pedro");
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getEmail(), "pedro@ebac.com");
        assertEquals(users.get(0).getName(), "Pedro");
    }

    @Test
    void updateById() {
        User user = logicsUsers.findUserById(4);
        user.setName("Carlos");
        user.setEmail("carlos@ebac.com");
        user.setPassword("56473829");
        logicsUsers.updateById(4, user);
        User user1 = logicsUsers.findUserById(4);
        assertEquals(user1.getName(), "Carlos");
        assertEquals(user1.getEmail(), "carlos@ebac.com");
        assertEquals(user1.getPassword(), "56473829");
    }

    @Test
    void updateByUserName() {
        logicsUsers.updateByUserName("Ana", "Gaby");
        User user = logicsUsers.findUserById(2);
        assertEquals(user.getName(), "Gaby");
    }

    @Test
    void updateByUserEmail() {
        logicsUsers.updateByUserEmail("carlos@ebac.com", "carlos22@ebac.com");
        User user = logicsUsers.findUserById(4);
        assertEquals(user.getEmail(), "carlos22@ebac.com");
    }

    @Test
    void updateByUserPassword() {
        logicsUsers.updateByUserPassword("carlos22@ebac.com", "56473829999");
        User user = logicsUsers.findUserById(4);
        assertEquals(user.getPassword(), "56473829999");
    }

    @Test
    void updateByUserEmailAndName() {
        logicsUsers.updateByUserEmailAndName("carlos22@ebac.com", "Carlos", "carlos2222@ebac.com","Carlitos");
        User user = logicsUsers.findUserById(4);
        assertEquals(user.getEmail(), "carlos2222@ebac.com");
        assertEquals(user.getName(), "Carlitos");
    }
}