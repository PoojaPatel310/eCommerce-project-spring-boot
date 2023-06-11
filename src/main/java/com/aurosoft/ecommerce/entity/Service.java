package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "title",nullable = false,length = 50)
    private String title;
    @Column(name = "detail",nullable = false,length = 255)
    private String detail;

    @Column(name = "icon",nullable = false,length = 255)
    private String icon;

    @Transient
    public String getPhotosImagePath() {
        if (icon == null || id == 0)
            return null;

        return "/service-icon/" + id + "/" + icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Service(int id, String title, String detail, String icon) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.icon = icon;
    }

    public Service() {
    }

}
