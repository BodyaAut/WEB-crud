package ru.hw.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import ru.hw.springmvc.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Autowired
    public UserDaoImpl(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<User> getUserList() {
        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUserById(long id) {
        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    public User getUserByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.email =:parEmail")
                .setParameter("parEmail", email)
                .getSingleResult();
    }

    public void saveUser(User user) {
        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteUserById(long id) {
        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateUserById(long id, User user) {
        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}