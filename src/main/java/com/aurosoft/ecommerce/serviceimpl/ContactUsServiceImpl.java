package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.ContactUs;
import com.aurosoft.ecommerce.repository.ContactUsRepository;
import com.aurosoft.ecommerce.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactUsServiceImpl implements ContactUsService {
    @Autowired
    ContactUsRepository repository;
    @Override
    public List<ContactUs> listAllContactUs() {
        return repository.findAll();
    }

    @Override
    public ContactUs getContactUsById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public ContactUs insertContactUs(ContactUs contactUs) {
        return repository.save(contactUs);
    }

    @Override
    public ContactUs updateContactUs(ContactUs contactUs) {
        return repository.save(contactUs);
    }

    @Override
    public int deleteContactUs(int id) {
        repository.deleteById(id);
        return id;
    }
}
