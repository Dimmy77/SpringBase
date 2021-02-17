package ru.geekbrain.purchase;

import ru.geekbrain.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

@Entity
@Table(name = "line_purchase")
public class LineOfPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer qty;

    @Column
    private BigDecimal priceOfPurchase;

    @Column
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id")
    private Purchase purchase;


    public LineOfPurchase(){
        priceOfPurchase.setScale(2, ROUND_HALF_DOWN);
    }

    public LineOfPurchase(Product product, Integer qty){
        this.product= product;
        this.qty=qty;
        this.price = product.getPrice();
        priceOfPurchase.setScale(2, ROUND_HALF_DOWN);
        priceOfPurchase=price.multiply(new BigDecimal(qty));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQty(){
        return qty;
    }

    public void setQty(Integer qty){
        this.qty=qty;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price=price;
    }

    public BigDecimal getPriceOfPurchase(){
        return priceOfPurchase;
    }

    public void setPriceOfPurchase(BigDecimal priceOfPurchase){
        this.priceOfPurchase=priceOfPurchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
