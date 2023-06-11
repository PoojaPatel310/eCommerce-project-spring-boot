package com.aurosoft.ecommerce.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "ContactUs")
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String fname;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lname;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "message", nullable = false, length = 255)
    private String message;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "enquiry_date", nullable = false)
    private Date enquiryDate;

    @PrePersist
    private void onCreate() {
        enquiryDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public ContactUs(Integer id, String fname, String lname, String phone, String email, String message, Date enquiryDate) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.message = message;
        this.enquiryDate = enquiryDate;
    }

    public ContactUs() {
    }
}
