package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "fname",nullable = false,length = 20)
    private String fname;
    @Column(name = "lname",nullable = false,length = 20)
    private  String lname;
    @Column(name = "city",nullable = false,length = 50)
    private  String city;
    @Column(name = "mobile",nullable = false,length = 20)
    private  String mobile;
    @Column(name = "email",nullable = false,length = 50)
    private String email;
    @Column(name = "password",nullable = false,length = 20)
    private  String password;
    @Column(name = "role",nullable = false,length = 20)
    private String role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<OrderMaster> orderList = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Cart> cartList = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<OrderMaster> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<OrderMaster> orderList) {
        this.orderList = orderList;
    }

    public Set<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(Set<Cart> cartList) {
        this.cartList = cartList;
    }

    public User(int id, String fname, String lname, String city, String mobile, String email, String password, String role, Set<OrderMaster> orderList, Set<Cart> cartList) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.city = city;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.role = role;
        this.orderList = orderList;
        this.cartList = cartList;
    }

    public User() {
    }
}
