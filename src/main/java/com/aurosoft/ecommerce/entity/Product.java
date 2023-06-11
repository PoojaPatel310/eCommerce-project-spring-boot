package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id",nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id",referencedColumnName = "id",nullable = false)
    private Brand brand;

    @Column(name = "name",nullable = false,length = 50)
    private String name;
    @Column(name = "detail",nullable = false,length = 255)
    private String detail;

    @Column(name = "image",nullable = false, length = 50)
    private String image;
    @Column(name = "mrp",nullable = false,length = 50)
    private Float mrp;
    @Column(name = "rate",nullable = false,length = 50)
    private Float rate;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetailList= new HashSet<>();

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<Cart> cartList= new HashSet<>();

    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == 0)
            return null;

        return "/product-photos/" + id + "/" + image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Set<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(Set<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Set<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(Set<Cart> cartList) {
        this.cartList = cartList;
    }

    public Product(int id, Category category, Brand brand, String name, String detail, String image, Float mrp, Float rate, Set<OrderDetail> orderDetailList, Set<Cart> cartList) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.detail = detail;
        this.image = image;
        this.mrp = mrp;
        this.rate = rate;
        this.orderDetailList = orderDetailList;
        this.cartList = cartList;
    }

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

}
