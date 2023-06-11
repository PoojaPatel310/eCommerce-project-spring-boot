package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;
    @Column(name = "logo",nullable = false,length = 50)
    private String logo;

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private Set<Product> productList= new HashSet<>();
    @Transient
    public String getPhotosImagePath() {
        if (logo == null || id == 0)
            return null;

        return "/brand-logo/" + id + "/" + logo;
    }

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    public Brand() {
    }

    public Brand(int id, String name, String logo, Set<Product> productList) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.productList = productList;
    }
}
