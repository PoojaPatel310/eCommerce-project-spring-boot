package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.service.BrandService;
import com.aurosoft.ecommerce.service.CategoryService;
import com.aurosoft.ecommerce.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
   private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<Product> list= productService.listAllProducts();
        m.addAttribute("list",list);
        return "admin/product/list";
    }

    @GetMapping("/new")
    public String showForm(Product product,Model m){
        List<Category> list = categoryService.listAllCategories();
        m.addAttribute("categoryList",list);
        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);
        return "admin/product/add";
    }


    @PostMapping("/insert")
    public String saveProduct(Product product, @RequestParam("photo1") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            product.setImage(fileName);
            Product savedProduct = productService.insertProduct(product);


            String uploadDir = "product-photos/" + savedProduct.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {
            product.setImage("noming.png");
            Product savedProduct = productService.insertProduct(product);

        }

        return "redirect:/product/list";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Product product = productService.getProductById(id);
        m.addAttribute("product",product);

        List<Category> list = categoryService.listAllCategories();
        m.addAttribute("categoryList",list);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);

        return "admin/product/edit";
    }

    @PostMapping(value="/update")
    public String updateProduct(Product product, @RequestParam("photo1") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if(fileName.length() > 3)
        {
            product.setImage(fileName);
            Product savedProduct = productService.updateProduct(product);


            String uploadDir = "product-photos/" + savedProduct.getId();

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else
        {

            Product savedProduct = productService.updateProduct(product);

        }

        return "redirect:/product/list";
    }


    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }
}
