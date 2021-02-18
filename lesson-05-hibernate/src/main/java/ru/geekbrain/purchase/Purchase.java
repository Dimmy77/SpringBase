package ru.geekbrain.purchase;

import ru.geekbrain.persist.User;
import ru.geekbrain.product.Product;
import ru.geekbrain.product.ProductRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String comment;
    @Column
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<LineOfPurchase> lineOfPurchaseList;

    public Purchase(){
        price=new BigDecimal(0);
        price.setScale(2, ROUND_HALF_DOWN);
    }

    public Purchase(Date date, String comment, User user, List<LineOfPurchase> lineOfPurchaseList){
        this.date=date;
        this.comment = comment;
        this.user=user;
        this.lineOfPurchaseList=lineOfPurchaseList;
        price.setScale(2, ROUND_HALF_DOWN);
        for(LineOfPurchase l: lineOfPurchaseList){
            price.add(l.getPriceOfPurchase());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date=date;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString(){
        return "\nUser{"+
                "id="+id+
                ", date='"+date+ '\''+
                ", comment='"+comment+ '\''+
                ", price='"+'\''+
                '}';
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public List<LineOfPurchase> getLineOfPurchase() {
        return lineOfPurchaseList;
    }

    public void setLineOfPurchase(List<LineOfPurchase> lineOfPurchaseList) {
        this.lineOfPurchaseList = lineOfPurchaseList;
    }
}
