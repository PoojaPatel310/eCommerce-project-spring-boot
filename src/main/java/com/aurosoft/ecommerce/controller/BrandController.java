package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.service.BrandService;
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
@RequestMapping("/brand")
public class BrandController {
    @Autowired
   private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<Brand> list= brandService.listAllBrand();
        m.addAttribute("list",list);
        return "admin/brand/list";
    }

    @GetMapping("/new")
    public String showForm(Brand brand){
        return "admin/brand/add";
    }


    @PostMapping("/insert")
    public String saveBrand(Brand brand, @RequestParam("photo1") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            brand.setLogo(fileName);
            Brand savedBrand = brandService.insertBrand(brand);


            String uploadDir = "brand-logo/" + savedBrand.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {
            brand.setLogo("noming.png");
            Brand savedBrand = brandService.insertBrand(brand);

        }

        return "redirect:/brand/list";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Brand brand = brandService.getBrandById(id);
        m.addAttribute("brand",brand);
        return "admin/brand/edit";
    }



    @PostMapping(value="/update")
    public String updateBrand( Brand brand, @RequestParam("photo1") MultipartFile multipartFile) throws IOException  {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            brand.setLogo(fileName);
            Brand savedBrand = brandService.updateBrand(brand);


            String uploadDir = "brand-logo/" + savedBrand.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {

            Brand savedBrand = brandService.updateBrand(brand);

        }

        return "redirect:/brand/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        brandService.deleteBrand(id);
        return "redirect:/brand/list";
    }
}
