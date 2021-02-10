package ru.geekbrain.persist;

import ru.geekbrain.persist.User;
import ru.geekbrain.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;


public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory){
        this.emFactory=emFactory;
    }

    public List<User> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<User> userList = em.createQuery("from User", User.class)
                .getResultList();
        em.close();
        return new ArrayList<>(userList);
    }

    public User findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        Object user = em.createNamedQuery("userById")
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return User.class.cast(user);
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
        if(user.getUsername().equals("") || user.getPassword().equals("") || user.getEmail().equals("")) return;

        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void update(User user) {
        if(user.getUsername().equals("") || user.getEmail().equals("") ||
                !user.getPassword().equals(user.getMatchingPassword()) || user.getEmail().equals("")) return;

        EntityManager em = emFactory.createEntityManager();
        User tmpUser = User.class.cast(em.createNamedQuery("userById")
                .setParameter("id", user.getId())
                .getSingleResult());

        if (tmpUser != null) {
            em.getTransaction().begin();
            tmpUser.setUsername(user.getUsername());
            if (!user.getIsBlock()) {
                tmpUser.setIsBlock(user.getIsBlock());
                tmpUser.setBlockDate(user.getBlockDate());
            }
            tmpUser.setPassword(user.getPassword());
            tmpUser.setMatchingPassword(user.getMatchingPassword());
            tmpUser.setEmail(user.getEmail());
        }
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
