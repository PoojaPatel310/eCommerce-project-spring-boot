package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.ContactUs;
import com.aurosoft.ecommerce.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactUsController {
    @Autowired
    private ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }
    @GetMapping("/list")
    public String listAll(Model m){
        List<ContactUs> list= contactUsService.listAllContactUs();
        m.addAttribute("list",list);
        return "admin/contact/list";
    }

    @GetMapping("/new")
    public String showForm(ContactUs contactUs){
        return "admin/contact/add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute("contactUs") ContactUs contactUs){
        contactUsService.insertContactUs(contactUs);
        return "redirect:/contact/list";
    }
    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        ContactUs contactUs = contactUsService.getContactUsById(id);
        m.addAttribute("contactUs",contactUs);
        return "admin/contact/edit";
    }

    @PostMapping(value="/update")
    public String update(@ModelAttribute("contactUs") ContactUs contactUs){

        if(contactUs.getEnquiryDate() == null) {
            contactUs.setEnquiryDate(new Date());
        }

        contactUsService.updateContactUs(contactUs);
        return "redirect:/contact/list";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        contactUsService.deleteContactUs(id);
        return "redirect:/contact/list";
    }
}

