package ru.geekbrain.persist;

import ru.geekbrain.persist.User;
import ru.geekbrain.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;


public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        EntityManager em = emFactory.createEntityManager();
        try {
            return em.createQuery("from User", User.class)
                    .getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public User findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public User findByName(String userName) {
        EntityManager em = emFactory.createEntityManager();
        Object user = em.createNamedQuery("userByName")
                .setParameter("username", userName)
                .getSingleResult();
        em.close();

        return User.class.cast(user);
    }

    public void insert(User user) {
        if (user.getUsername().equals("") || user.getPassword().equals("") || user.getEmail().equals("")) return;

        EntityManager em = emFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {

                em.close();
            }
        }
    }

    public void update(User user) {
        if (user.getUsername().equals("") || user.getEmail().equals("") ||
                !user.getPassword().equals(user.getMatchingPassword()) || user.getEmail().equals("")) return;

        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {

                em.close();
            }
        }
    }

    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            User user1 = em.find(User.class, id);
            if (user1 != null) {
                em.remove(user1);
            }
            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function){
        EntityManager em= emFactory.createEntityManager();
        try{
            return function.apply(em);
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        }catch (Exception exception){
            em.getTransaction().rollback();
        }finally {
            if(em != null){
                em.close();
            }
        }
    }

}
