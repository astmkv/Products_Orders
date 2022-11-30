package com.example.products.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="orders_t", schema = "products_orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "client")
    private String client;

    @Basic
    @Column(nullable = false, length = 200)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "order")
    private Set<Product> products;

}
