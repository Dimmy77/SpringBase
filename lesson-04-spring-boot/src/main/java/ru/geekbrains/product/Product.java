package ru.geekbrains.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Product {
    private Long id;

    @NotEmpty
    private String productName;

    private String description;

    @NotNull
    private Float price;

    public Product(){
        this.price=(float)1;
    }

    public Product(String productName){
        this.productName=productName;
        this.description="";
        this.price=(float)1;
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
}
