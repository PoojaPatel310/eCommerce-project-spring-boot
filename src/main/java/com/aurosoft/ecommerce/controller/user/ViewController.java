package com.aurosoft.ecommerce.controller.user;

import com.aurosoft.ecommerce.entity.*;
import com.aurosoft.ecommerce.service.*;
import com.aurosoft.ecommerce.util.Helper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

import java.util.List;


@Controller
public class ViewController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CompanyDetailService companyDetailService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ContactUsService contactUsService;

    public ViewController(CategoryService categoryService, BrandService brandService, CartService cartService, ProductService productService, UserService userService, OrderMasterService orderMasterService, OrderDetailService orderDetailService, CompanyDetailService companyDetailService, ServiceService serviceService, ContactUsService contactUsService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
        this.orderMasterService = orderMasterService;
        this.orderDetailService = orderDetailService;
        this.companyDetailService = companyDetailService;
        this.serviceService = serviceService;
        this.contactUsService = contactUsService;
    }

    @GetMapping("/template")
    public String template(){
        return "user/template";
    }

    @GetMapping("/index")
    public String index(@RequestParam (value = "category_id",defaultValue = "0")int category_id,
                        @RequestParam(value = "del_id",defaultValue = "0") int del_id,Model m,
                        HttpSession session){

        //temp....................
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);

        List<Brand> list1 = brandService.listAllBrand();
       m.addAttribute("brandList",list1);
        int uid = 0;
       if(session.getAttribute("uid") != null) {
           uid = (int) session.getAttribute("uid");
       }
        List<Cart> carts  = new ArrayList<>();
        if(uid >0) {
            User user1 = userService.getUserById(uid);
            carts = cartService.getCartByUser(user1);
        }
        m.addAttribute("carts",carts);

        if(del_id > 0)
        {
            cartService.deleteCart(del_id);
        }

        float total = 0;
        for(int i=0;i<carts.size();i++)
        {
            total += carts.get(i).getQty()*carts.get(i).getProduct().getRate();
        }
        m.addAttribute("total", total);

        int totalProducts = carts.stream().mapToInt(Cart::getQty).sum();
        m.addAttribute("totalProducts", totalProducts);

        //--------------------------------------------------

        List<Product> productCategoryList  = new ArrayList<>();
        if(category_id >0) {
            Category category = categoryService.getCategoryById(category_id);
            productCategoryList = productService.getProductByCategory(category);
        } else {
            productCategoryList = productService.listAllProducts();
        }
        m.addAttribute("productList",productCategoryList);


        List<Category> list_3 = categoryService.listAllCategories().subList(2,5);
        m.addAttribute("list_3",list_3);

        List<Product> productList = productService.listAllProducts().subList(0,6);
        m.addAttribute("productList",productList);

        List<Product> productList_4 = productService.listAllProducts().subList(0,4);
        m.addAttribute("productList_4",productList_4);

        Category banner1 = categoryService.getCategoryById(2);
        m.addAttribute("banner1",banner1);

        Category banner2 = categoryService.getCategoryById(1);
        m.addAttribute("banner2",banner2);

        List<Service> serviceList = serviceService.listAllService().subList(0,3);
        m.addAttribute("serviceList",serviceList);



        return "index";
    }



    @GetMapping("/shop")
    public  String showShopForm(@RequestParam (value = "category_id",defaultValue = "0")int category_id,
                                @RequestParam (value = "brand_id",defaultValue = "0")int brand_id,Model m){

        List<Product> productList  = new ArrayList<>();
        if(category_id >0) {
            Category category = categoryService.getCategoryById(category_id);
            productList = productService.getProductByCategory(category);
        }
        else {
            productList = productService.listAllProducts();
        }
        m.addAttribute("productList",productList);



        List<Brand> brandList = brandService.listAllBrand();
        m.addAttribute("brandList",brandList);

        List<Product> productBrandList  = new ArrayList<>();
        if(brand_id >0) {
            Brand brand= brandService.getBrandById(brand_id);
            productBrandList = productService.getProductByBrand(brand);
        }
        else {
            productBrandList = productService.listAllProducts();
        }
        m.addAttribute("productBrandList",productBrandList);


//*-------------------------------------template-----------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);

    //-----------------------------------------------------------------------------------------------
        return "user/shop";
    }

    @GetMapping("/product")
    public  String showProductForm(@RequestParam("id") int id, Model m){

        Product singleProduct = productService.getProductById(id);
        m.addAttribute("singleProduct",singleProduct);


        //------------------------template---------------------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);
        //-------------------------------------------------------------------------------------------------

        return "user/product";
    }

    @GetMapping("/contact")
    public String contactUs(ContactUs contactUs, Model m){


        //------------------------template------------------------------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);
        //----------------------------------------------------------------------------------------------------

        return "user/contact";
    }

    @PostMapping("/contact")
    public String contactUs(@ModelAttribute("contactUs") ContactUs contactUs){
        contactUsService.insertContactUs(contactUs);

        return "redirect:/contact";
    }



    @GetMapping("/check-out")
    public String checkOut(Model m,HttpSession session){

        int uid = (int)session.getAttribute("uid");

        List<Cart> carts  = new ArrayList<>();
        if(uid >0) {
            User user1 = userService.getUserById(uid);
          carts = cartService.getCartByUser(user1);
        }
        m.addAttribute("carts",carts);

        float total = 0;
        for(int i=0;i<carts.size();i++)
        {
            total += carts.get(i).getQty()*carts.get(i).getProduct().getRate();
        }
        m.addAttribute("total", total);
//------------template------------------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);
        //----------------------------------------------------------------------------

        return "user/check-out";
    }

    @GetMapping("/cart")
    public String cart(@RequestParam(value = "del_id",defaultValue = "0") int del_id,
                       @RequestParam(value = "add_id",defaultValue = "0") int add_id,
                      Model m,HttpSession session ){

        if(!Helper.checkUserRole() && !Helper.checkAdminRole()){
            return "redirect:/login ";
        }
        int uid = (int)session.getAttribute("uid");
        if(add_id > 0)
        {
            Product product = productService.getProductById(add_id);

            User user1 = userService.getUserById(uid);
            Cart cart = new Cart(user1,product,1);

            cart.setRate(product.getRate());
            cart.setAmount(product.getRate()*cart.getQty());
            cartService.insertCart(cart);
        }

        List<Cart> carts  = new ArrayList<>();
        if(uid >0) {
            User user1 = userService.getUserById(uid);
            carts = cartService.getCartByUser(user1);
        }
        m.addAttribute("carts",carts);

        if(del_id > 0)
        {
            cartService.deleteCart(del_id);
        }

        float total = 0;
        for(int i=0;i<carts.size();i++)
        {
            total += carts.get(i).getQty()*carts.get(i).getProduct().getRate();
        }
        m.addAttribute("total", total);

//--------------------------------template--------------------------------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);
        //----------------------------------------------------------------------------------------------

        return "user/cart";
    }


    @GetMapping("/login")
    public String login(Model m) {

        //------------template------------------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);
        //----------------------------------------------------------------------------
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session)
    {
        User user = userService.findByEmailAndPassword(email, password);

        if (user != null) {
            session.setAttribute("uname", user.getFname() + "   " + user.getLname());
            session.setAttribute("fname", user.getFname());
            session.setAttribute("uid", user.getId());
            session.setAttribute("urole", user.getRole());
            session.setAttribute("email",user.getEmail());

            if (Helper.checkUserRole()) {
                session.setAttribute("msg", "You are successfully login..");
                return "redirect:/index";

            } else {
                session.setAttribute("msg", "You are successfully login..");
                return "admin/template";
            }
        }else
        {
            session.setAttribute("msg","Wrong Username or Password..");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        if( session.getAttribute("uname") != null)
            session.removeAttribute("uname");

        if(session.getAttribute("uid") != null)
            session.removeAttribute("uid");
        if(session.getAttribute("urole") != null)
            session.removeAttribute("urole");

        session.setAttribute("msg","You are successfully logged-out from system..");
        return "redirect:/login";
    }

    @GetMapping("/faq")
    public String faq(Model m){
//------------template------------------------------------------------------------
        List<Category> categoryList = categoryService.listAllCategories();
        m.addAttribute("categoryList",categoryList);

        List<Brand> list1 = brandService.listAllBrand();
        m.addAttribute("brandList",list1);

        List<CompanyDetail> companyDetail = companyDetailService.listAllCompanyDetail();
        m.addAttribute("companyDetail",companyDetail);
        //----------------------------------------------------------------------------

        return "user/faq";
    }

}
