package ru.geekbrain.product;


import ru.geekbrain.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory){
        this.emFactory=emFactory;
    }

//    private AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("allProducts", Product.class)
                        .getResultList()
        );
    }

    public Product findById(long id) {
        return executeForEntityManager(
                em -> em.createNamedQuery("productById", Product.class)
                        .getSingleResult()
        );
    }

    public Product findByName(String productName) {
        return executeForEntityManager(
                em -> em.createNamedQuery("productByName", Product.class)
                        .getSingleResult()
        );
    }

    public void insert(Product product) {
        if(product.getProductName().equals("") || product.getPrice().floatValue() <= 0) return;
        executeInTransaction(em -> em.persist(product));
    }

    public void update(Product product) {
        if(product.getProductName().equals("") || product.getPrice().floatValue() <= 0) return;
        executeInTransaction(em ->em.merge(product));
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
        });
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
