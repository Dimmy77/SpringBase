package ru.geekbrain.product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_tbl")
@NamedQueries({
        @NamedQuery(name = "productByName", query = "from Product p where p.productName=:productName"),
        @NamedQuery(name = "productById", query = "from Product p where p.id=:id"),
        @NamedQuery(name = "allProducts", query = "from Product")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 45, nullable = false, unique = true)
    private String productName;

    @Column
    private String description;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private Date createData;

    @Column
    private Date blockDate;

    public Product(){
        this.price=0.1F;
        createData = new Date(System.currentTimeMillis());
        isAvailable = true;
    }

    public Product(String productName){
        this.productName=productName;
        price=0.1F;
        description="";
        createData = new Date(System.currentTimeMillis());
        isAvailable = true;
    }

    public Product(String productName, float price){
        this.productName=productName;
        this.price = price;
        description="";
        createData = new Date(System.currentTimeMillis());
        isAvailable = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString(){
        return "Product{"+
                "id="+id+
                ", product name='"+productName+ '\''+
                ", description='"+description+ '\''+
                ", price='"+price+ '\''+
                ", date created='"+createData+ '\''+
                ", is available='"+isAvailable+ '\''+
                ", date block='"+blockDate+ '\''+
                '}'+"\n";
    }

    public Date getCreatedData() {
        return createData;
    }

    public void setCreatedData(Date createData) {
        this.createData = createData;
    }

    public void setBlockDate(Date blockDate) {
        this.blockDate = blockDate;
    }

    public Date getBlockDate() {
        return blockDate;
    }
}
