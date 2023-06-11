package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.CompanyDetail;
import com.aurosoft.ecommerce.service.CompanyDetailService;
import com.aurosoft.ecommerce.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/company-detail")
public class CompanyDetailController {
    @Autowired
   private CompanyDetailService companyDetailService;

    public CompanyDetailController(CompanyDetailService companyDetailService) {
        this.companyDetailService = companyDetailService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<CompanyDetail> list= companyDetailService.listAllCompanyDetail();
        m.addAttribute("list",list);
        return "admin/company-detail/list";
    }

    @GetMapping("/new")
    public String showForm(CompanyDetail companyDetail){
        return "admin/company-detail/add";
    }


    @PostMapping("/insert")
    public String saveCompanyDetail(CompanyDetail companyDetail, @RequestParam("photo1") MultipartFile multipartFile1,
                                    @RequestParam("photo2") MultipartFile multipartFile2) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
        if(fileName.length() > 3)
        {
            companyDetail.setHeaderLogo(fileName);
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
            String uploadDir = "company-logo/" + savedCompanyDetail.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile1);
        }
        else
        {
            companyDetail.setHeaderLogo("noming.png");
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
        }
        String fileName1 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
        if(fileName1.length() > 3)
        {
            companyDetail.setFooterLogo(fileName1);
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
            String uploadDir = "company-logo/" + savedCompanyDetail.getId();
            FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile2);
        }
        else
        {
            companyDetail.setFooterLogo("noming.png");
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
        }

        return "redirect:/company-detail/list";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        CompanyDetail companyDetail = companyDetailService.getCompanyDetailById(id);
        m.addAttribute("companyDetail",companyDetail);
        return "admin/company-detail/edit";
    }



    @PostMapping(value="/update")
    public String updateCompanyDetail( CompanyDetail companyDetail, @RequestParam("photo1") MultipartFile multipartFile1,
                                       @RequestParam("photo2") MultipartFile multipartFile2)  throws IOException  {

        String fileName = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
        if(fileName.length() > 3)
        {
            companyDetail.setHeaderLogo(fileName);
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
            String uploadDir = "company-logo/" + savedCompanyDetail.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile1);
        }
        else
        {
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
        }
        String fileName1 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
        if(fileName1.length() > 3)
        {
            companyDetail.setFooterLogo(fileName1);
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
            String uploadDir = "company-logo/" + savedCompanyDetail.getId();
            FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile2);
        }
        else
        {
            CompanyDetail savedCompanyDetail = companyDetailService.updateCompanyDetail(companyDetail);
        }

        return "redirect:/company-detail/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        companyDetailService.deleteCompanyDetail(id);
        return "redirect:/company-detail/list";
    }
    
}
