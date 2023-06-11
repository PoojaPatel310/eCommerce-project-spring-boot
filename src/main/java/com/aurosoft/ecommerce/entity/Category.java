package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;
    @Column(name = "image",nullable = false,length = 50)
    private String image;


    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == 0)
            return null;

        return "/category-photos/" + id + "/" + image;
    }

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private Set<Product> productList= new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    public Category(int id, String name, String image, Set<Product> productList) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.productList = productList;
    }

    public Category() {
    }
}
