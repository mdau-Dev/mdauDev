package com.mdauDev.SecurityCurity.Entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 7
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long productId;
    private String name;
    private int price;
    private int quantity;

    @ManyToOne(
            cascade = CascadeType.ALL
    )@JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId"
    )
    private Customer customer;

    public Product(long productId, String name, int price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    public Product(String name, int price, int quantity, Customer customer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.customer = customer;
    }

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
