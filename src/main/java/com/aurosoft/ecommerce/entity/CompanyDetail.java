package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "company-details")
public class CompanyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "name",nullable = false,length = 50)
    private String name;
    @Column(name = "header_logo",nullable = false,length = 50)
    private String headerLogo;
    @Column(name = "footer_logo",nullable = false,length = 50)
    private String footerLogo;
    @Column(name = "email",nullable = false,length = 50)
    private String email;
    @Column(name = "mobile",nullable = false,length = 50)
    private String mobile;
    @Column(name = "facebook",nullable = false,length = 50)
    private String facebook;
    @Column(name = "twitter",nullable = false,length = 50)
    private String twitter;
    @Column(name = "linkedin",nullable = false,length = 50)
    private  String linkedin;
    @Column(name = "pinterest",nullable = false,length = 50)
    private  String pinterest;
    @Column(name = "address1",nullable = false,length = 150)
    private String address1;
    @Column(name = "address2",nullable = false,length = 150)
    private String address2;
    @Column(name = "copyWrite",nullable = false,length = 100)
    private String copyWrite;

    @Transient
    public String getHeaderLogoPath() {
        if (headerLogo == null || id == 0)
            return null;

        return "/company-logo/" + id + "/" + headerLogo;
    }
    @Transient
    public String getFooterLogoPath() {
        if (footerLogo == null || id == 0)
            return null;

        return "/company-logo/" + id + "/" + footerLogo;
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

    public String getHeaderLogo() {
        return headerLogo;
    }

    public void setHeaderLogo(String headerLogo) {
        this.headerLogo = headerLogo;
    }

    public String getFooterLogo() {
        return footerLogo;
    }

    public void setFooterLogo(String footerLogo) {
        this.footerLogo = footerLogo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPinterest() {
        return pinterest;
    }

    public void setPinterest(String pinterest) {
        this.pinterest = pinterest;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCopyWrite() {
        return copyWrite;
    }

    public void setCopyWrite(String copyWrite) {
        this.copyWrite = copyWrite;
    }

    public CompanyDetail() {
    }

    public CompanyDetail(int id, String name, String headerLogo, String footerLogo, String email, String mobile, String facebook, String twitter, String linkedin, String pinterest, String address1, String address2, String copyWrite) {
        this.id = id;
        this.name = name;
        this.headerLogo = headerLogo;
        this.footerLogo = footerLogo;
        this.email = email;
        this.mobile = mobile;
        this.facebook = facebook;
        this.twitter = twitter;
        this.linkedin = linkedin;
        this.pinterest = pinterest;
        this.address1 = address1;
        this.address2 = address2;
        this.copyWrite = copyWrite;
    }
}
