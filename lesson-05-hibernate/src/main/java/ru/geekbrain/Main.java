package ru.geekbrain;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args){
        //SessionFactory sessionFactory = new Configuration()
        EntityManagerFactory emFactory = new Configuration() //то же самое что SessionFactory, только реализован в стандарте GPA
                .configure("product.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

//INSERT
//        em.getTransaction().begin();
//        User user = new User("User3", "user3", "user3@mail.ru");
//        em.persist(user);
//        em.getTransaction().commit();
//        em.close();

//SELECT
        // запрос единственного пользователя
//        User user1 = em.find(User.class, 1L);
//        System.out.println("\nFind 1 user:"+user1);
//
//        //на HQL (hibernate query language) или JPQL
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println("\nFull list of user (HQL):"+userList);
//
//
//        Object user2 = em.createQuery("from User u where u.username=:username")
//                .setParameter("username", "User3")
//                .getSingleResult();
//        System.out.println("\nFind 1 user (HQL):"+user2);
//
//        //SQL
//        userList = em.createNativeQuery("select * from user_tbl", User.class)
//                .getResultList();
//        System.out.println("\nFull list of user (SQL):"+userList);
//
//        //с помощью аннотаций
//        em.createNamedQuery("userByName")
//                .setParameter("username", "User2")
//                .getSingleResult();
//        System.out.println("\nFind 1 user (annotation):"+user2);
//
//        em.close();

// UPDATE
//        User user = em.find(User.class,1L);
//        em.getTransaction().begin();
//        user.setPassword("user1"); //Здесь работает и getPassword
//        em.getTransaction().commit();
//        em.close();

//DELETE
        em.getTransaction().begin();
//        User user = em.find(User.class, 5L);
//        if (user != null) {
//            em.remove(user);
//        }

        //JPQL
        em.createQuery("delete from User where username=:username")
                .setParameter("username", "User2")
                .executeUpdate();

        em.getTransaction().commit();

//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);

        em.close();
    }
}
