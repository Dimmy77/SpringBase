package ru.geekbrain.product;

import ru.geekbrain.purchase.LineOfPurchase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

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
    private Long product_id;

    @Column (length = 45, nullable = false, unique = true)
    private String productName;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private Date createData;

    @Column
    private Date blockDate;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<LineOfPurchase> lineOfPurchaseList;

    public Product(){
        this.price= new BigDecimal(0.1);
        price.setScale(2, ROUND_HALF_DOWN);
        createData = new Date(System.currentTimeMillis());
        isAvailable = true;
    }

    public Product(String productName){
        this.productName=productName;
        this.price= new BigDecimal(0.1);
        price.setScale(2, ROUND_HALF_DOWN);
        description="";
        createData = new Date(System.currentTimeMillis());
        isAvailable = true;
    }

    public Product(String productName, BigDecimal price){
        this.productName=productName;
        price.setScale(2, ROUND_HALF_DOWN);
        this.price = price;
        description="";
        createData = new Date(System.currentTimeMillis());
        isAvailable = true;
    }

    public Long getId() {
        return product_id;
    }

    public void setId(Long id) {
        this.product_id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
                "id="+product_id+
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

    public List<LineOfPurchase> getLineOfPurchaseList() {
        return lineOfPurchaseList;
    }

    public void setLineOfPurchaseList(List<LineOfPurchase> lineOfPurchaseList) {
        this.lineOfPurchaseList = lineOfPurchaseList;
    }
}
