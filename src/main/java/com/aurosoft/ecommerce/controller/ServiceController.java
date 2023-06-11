package com.aurosoft.ecommerce.controller;
import com.aurosoft.ecommerce.entity.Service;
import com.aurosoft.ecommerce.service.ServiceService;
import com.aurosoft.ecommerce.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService  serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
    @GetMapping("/list")
    public String listALl(Model m){
        List<Service> list = serviceService.listAllService();
        m.addAttribute("list",list);
        return "admin/service/list";
    }

    @GetMapping("/new")
    public String showForm(Service service){
      return "admin/service/add";
    }

    @PostMapping("/insert")
    public String saveService(Service service, @RequestParam("icon1") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            service.setIcon(fileName);
            Service savedService = serviceService.insertService(service);

            String uploadDir = "service-icon/" + savedService.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {
            service.setIcon("noming.png");
            Service savedService = serviceService.insertService(service);
        }

        return "redirect:/service/list";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id,Model m){
        Service service = serviceService.getServiceById(id);
        m.addAttribute("service",service);
        return "admin/service/edit";
    }

    @PostMapping("/update")
    public String updateService(Service service, @RequestParam("icon1") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            service.setIcon(fileName);
            Service savedService = serviceService.updateService(service);

            String uploadDir = "service-icon/" + savedService.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {
            Service savedService = serviceService.updateService(service);
        }

        return "redirect:/service/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        serviceService.deleteService(id);
        return "redirect:/service/list";
    }




}
