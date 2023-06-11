package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
   private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<Category> list= categoryService.listAllCategories();
        m.addAttribute("list",list);
        return "admin/category/list";
    }

    @GetMapping("/new")
    public String showForm(Category category){
        return "admin/category/add";
    }


    @PostMapping("/insert")
    public String saveCategory(Category category, @RequestParam("photo1") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            category.setImage(fileName);
           Category savedCategory = categoryService.insertCategory(category);


            String uploadDir = "category-photos/" + savedCategory.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {
            category.setImage("noming.png");
            Category savedCategory = categoryService.insertCategory(category);

        }

        return "redirect:/category/list";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Category category = categoryService.getCategoryById(id);
        m.addAttribute("category",category);
        return "admin/category/edit";
    }



    @PostMapping(value="/update")
    public String updateCategory( Category category, @RequestParam("photo1") MultipartFile multipartFile) throws IOException  {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            category.setImage(fileName);
            Category savedCategory = categoryService.updateCategory(category);


            String uploadDir = "category-photos/" + savedCategory.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {

            Category savedCategory = categoryService.updateCategory(category);

        }

        return "redirect:/category/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "redirect:/category/list";
    }
    
}
