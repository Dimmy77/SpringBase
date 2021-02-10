package ru.geekbrain.product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory){
        this.emFactory=emFactory;
    }

//    private AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> productList = em.createQuery("from Product", Product.class)
                .getResultList();
        em.close();
        return new ArrayList<>(productList);
    }

    public Product findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        Object product = em.createNamedQuery("productById")
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return Product.class.cast(product);
    }

    public Product findByName(String productName) {
        EntityManager em = emFactory.createEntityManager();
        Object product = em.createNamedQuery("productByName")
                .setParameter("productName", productName)
                .getSingleResult();
        em.close();
        return Product.class.cast(product);
    }

    public void insert(Product product) {
        if(product.getProductName().equals("") || product.getPrice() <= 0) return;

        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Product product) {
        if(product.getProductName().equals("") || product.getPrice() <= 0) return;

        EntityManager em = emFactory.createEntityManager();
        Product tmpProduct = Product.class.cast(em.createNamedQuery("productById")
                .setParameter("id", product.getId())
                .getSingleResult());

        if (tmpProduct != null) {
            em.getTransaction().begin();
            tmpProduct.setProductName(product.getProductName());
            if (!product.getAvailable()) {
                tmpProduct.setAvailable(product.getAvailable());
                tmpProduct.setBlockDate(product.getBlockDate());
            }
            tmpProduct.setPrice(product.getPrice());
//            product.setCreatedData(product.getCreatedData());
            tmpProduct.setDescription(product.getDescription());
        }
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Product where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
