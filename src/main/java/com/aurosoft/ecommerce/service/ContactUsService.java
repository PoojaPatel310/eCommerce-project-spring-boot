package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.ContactUs;

import java.util.List;

public interface ContactUsService {

    List<ContactUs> listAllContactUs();
    ContactUs getContactUsById(int id);
    ContactUs insertContactUs(ContactUs contactUs);
    ContactUs updateContactUs(ContactUs contactUs);
    int deleteContactUs(int id);

}
