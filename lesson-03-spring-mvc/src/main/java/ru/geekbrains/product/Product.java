package ru.geekbrains.product;

import javax.validation.constraints.NotEmpty;

public class Product {
    private Long id;

    @NotEmpty
    private String productName;

    private String description;

//    @NotEmpty
    private Float price;

    public Product(){
        this.price=(float)0.0;
    }

    public Product(String productName){
        this.productName=productName;
        this.description="";
        this.price=(float)0.0;
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
