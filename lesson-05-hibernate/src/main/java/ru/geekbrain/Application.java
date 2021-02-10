package ru.geekbrain;

import org.hibernate.cfg.Configuration;
import ru.geekbrain.persist.User;
import ru.geekbrain.persist.UserRepository;
import ru.geekbrain.product.Product;
import ru.geekbrain.product.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Application {

    public static void main(String[] args){

        EntityManagerFactory emProductFactory = new Configuration()
                .configure("product.cfg.xml")
                .buildSessionFactory();

        EntityManagerFactory emUserFactory = new Configuration()
                .configure("user.cfg.xml")
                .buildSessionFactory();

        ProductRepository productRepository = new ProductRepository(emProductFactory);

//        productRepository.insert(new Product("Orange"));
//        productRepository.insert(new Product("Banana"));
//        productRepository.insert(new Product("Berry"));
        System.out.println("\nFull list of product:"+productRepository.findAll());

//        Product product = productRepository.findById(2L);
//        product.setDescription("From Africa");
//        productRepository.update(product);
//        System.out.println("\nFind 1 product:"+productRepository.findById(2L));


//        productRepository.delete(productRepository
//                .findByName("Berry")
//                .getId());
//        System.out.println("\nFull list of user:"+productRepository.findAll());


        UserRepository userRepository = new UserRepository(emUserFactory);
//        userRepository.insert(new User("User1", "user1","user1@mail.ru"));
//        userRepository.insert(new User("User2", "user2","user2@mail.ru"));
//        userRepository.insert(new User("User3", "user3","user3@mail.ru"));
        System.out.println("\nFull list of user:"+userRepository.findAll());

//        User user = userRepository.findByName("User1");
//        user.setPassword("User1");
//        userRepository.update(user);
//        System.out.println("\nFind 1 user:"+userRepository.findByName("User1"));


//        userRepository.delete(userRepository
//                .findByName("User2")
//                .getId());
//        System.out.println("\nFull list of user:"+userRepository.findAll());
    }
}
