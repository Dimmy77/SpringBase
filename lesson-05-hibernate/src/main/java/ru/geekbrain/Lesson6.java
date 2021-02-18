package ru.geekbrain;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Lesson6 {

    public static void main(String[] args){

//        EntityManagerFactory emProductFactory = new Configuration()
//                .configure("product.cfg.xml")
//                .buildSessionFactory();

        EntityManagerFactory emUserFactory = new Configuration()
                .configure("user.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emUserFactory.createEntityManager();

        em.getTransaction().begin();

//        UserRepository userRepository = new UserRepository(emUserFactory);
//        User user1 = userRepository.findByName("User2");

//        List<Purchase> purchaseList = new ArrayList<>();
//        purchaseList.add(new Purchase(new Date(System.currentTimeMillis()), "",userRepository.findByName("User2")));
//        purchaseList.add(new Purchase(new Date(System.currentTimeMillis()), "",userRepository.findByName("User1")));
//
//        purchaseList.forEach(em::persist);
//
//        em.getTransaction().commit();
//
//        User user = em.find(User.class, 1L);
//
//        user.getPurchaseList().forEach(System.out::println);


//        List<Purchase> purchaseList = em.createQuery("select c from User u " +
//                "inner join Purchase c on u.id = c.user.id"+
//                "where c.type = 'mobile phone'",Purchase.class).getResultList();
//
//        purchaseList.forEach(System.out::println);

        em.close();


    }
}
