package com.business.libraryLogic;

import com.business.classBase.User;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class LogicsUsers {
    private EntityManager entityManager;
    public LogicsUsers(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    public void addUser(User user){
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            System.out.println("Usuario agregado");
        }
        catch (Exception e){
            transaction.rollback();
            System.out.println("Usuario no agregado algo salio mal");
            throw e;
        }

    }
    public void deleteUser(User user){
        EntityTransaction transaction=entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.remove(user);
            transaction.commit();
            System.out.println("Usuario eliminado");
        }
        catch (Exception e){
            transaction.rollback();
            System.out.println("Usuario no eliminado algo salio mal");
            throw e;
        }

    }
    public void updateUser(User user){
        EntityTransaction transaction=entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
            System.out.println("Usuario actualizado");
        }
        catch (Exception e){
            transaction.rollback();
            System.out.println("Usuario no actualizado algo salio mal");
            throw e;
        }
    }
    public User findUserById(int id){
        return entityManager.find(User.class,id);
    }
    public List<User> findAllUsers(){
        String sql="SELECT * FROM user";
        return entityManager.createNativeQuery(sql,User.class).getResultList();
    }
    public List<User> findByUserName(String name){
        String sql="SELECT * FROM user WHERE name =?";
        return entityManager.createNativeQuery(sql,User.class).setParameter(1,name).getResultList();
    }
    public List<User> findByUserEmail(String email){
        String sql="SELECT * FROM user WHERE email =?";
        return entityManager.createNativeQuery(sql,User.class).setParameter(1,email).getResultList();
    }
    public List<User> findByUserEmailAndName(String email, String name){
        String sql="SELECT * FROM user WHERE email =? AND name =?";
        return entityManager.createNativeQuery(sql,User.class).setParameter(1,email).setParameter(2,name).getResultList();
    }
    public void updateById(int id,User user){
        User user1 = findUserById(id);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        updateUser(user1);
    }
    public void updateByUserName(String name, String newName){
        List<User> users = findByUserName(name);
        if (users.size()>0&&users.size()==1){
            User user1 = users.get(0);
            user1.setName(newName);
            updateUser(user1);
        }
        if (users.size()>1){
            System.out.println("Hay mas de un usuario con el mismo nombre");
        }
        if (users.size()==0){
            System.out.println("No se encontro el usuario");
        }
    }
    public void updateByUserEmail(String email, String newEmail){
        List<User> users = findByUserEmail(email);
        if (users.size()>0&&users.size()==1){
            User user1 = users.get(0);
            user1.setEmail(newEmail);
            updateUser(user1);
        }
        if (users.size()>1){
            System.out.println("Hay mas de un usuario con el mismo correo");
        }
        if (users.size()==0){
            System.out.println("No se encontro el usuario");
        }
    }
    public void updateByUserPassword(String email, String newPassword){
        List<User> users = findByUserEmail(email);
        if (users.size()>0&&users.size()==1){
            User user1 = users.get(0);
            user1.setPassword(newPassword);
            updateUser(user1);
        }
        if (users.size()>1){
            System.out.println("Hay mas de un usuario con el mismo correo");
        }
        if (users.size()==0){
            System.out.println("No se encontro el usuario");
        }
    }
    public void updateByUserEmailAndName(String email, String name, String newEmail, String newName){
        List<User> users = findByUserEmailAndName(email, name);
        if (users.size()>0&&users.size()==1){
            User user1 = users.get(0);
            user1.setEmail(newEmail);
            user1.setName(newName);
            updateUser(user1);
        }
        if (users.size()>1){
            System.out.println("Hay mas de un usuario con el mismo correo y nombre");
        }
        if (users.size()==0){
            System.out.println("No se encontro el usuario");
        }
    }

}
