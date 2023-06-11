package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.CartService;
import com.aurosoft.ecommerce.service.ProductService;
import com.aurosoft.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public CartController(CartService cartService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<Cart> list= cartService.listAllCarts();
        m.addAttribute("list",list);
        return "admin/cart/list";
    }

    @GetMapping("/new")
    public String showForm(Cart cart,Model m){

        List<User> list = userService.listAllUsers();
        m.addAttribute("userList",list);

        List<Product> list1 = productService.listAllProducts();
        m.addAttribute("productList",list1);

        return "admin/cart/add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute("cart") Cart cart,@ModelAttribute("product") Product product){
        cart.setRate(product.getRate());
        cart.setAmount(product.getRate()*cart.getQty());
        cartService.insertCart(cart);
        return "redirect:/cart/list";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Cart cart = cartService.getCartById(id);
        m.addAttribute("cart",cart);


        List<User> list = userService.listAllUsers();
        m.addAttribute("userList",list);

        List<Product> list1 = productService.listAllProducts();
        m.addAttribute("productList",list1);


        return "admin/cart/edit";
    }

    @PostMapping(value="/update")
    public String update(@ModelAttribute("cart") Cart cart,@ModelAttribute("product") Product product){
        cart.setRate(product.getRate());
        cart.setAmount(cart.getRate()*cart.getQty());
        cartService.updateCart(cart);
        return "redirect:/cart/list";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        cartService.deleteCart(id);
        return "redirect:/cart/list";
    }
}
