package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.OrderDetail;
import com.aurosoft.ecommerce.entity.OrderMaster;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.OrderDetailService;
import com.aurosoft.ecommerce.service.OrderMasterService;
import com.aurosoft.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order-detail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private ProductService productService;

    public OrderDetailController(OrderDetailService orderDetailService, OrderMasterService orderMasterService, ProductService productService) {
        this.orderDetailService = orderDetailService;
        this.orderMasterService = orderMasterService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<OrderDetail> list= orderDetailService.listAllOrderDetails();
        m.addAttribute("list",list);
        return "admin/order-detail/list";
    }

    @GetMapping("/new")
    public String showForm(OrderDetail orderDetail,Model m){

        List<OrderMaster> list = orderMasterService.listAllOrderMasters();
        m.addAttribute("orderList",list);

        List<Product> list1 = productService.listAllProducts();
        m.addAttribute("productList",list1);

        return "admin/order-detail/add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute("orderDetail") OrderDetail orderDetail){
        orderDetailService.insertOrderDetail(orderDetail);
        return "redirect:/order-detail/list";
    }
    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
        m.addAttribute("orderDetail",orderDetail);

        List<OrderMaster> list = orderMasterService.listAllOrderMasters();
        m.addAttribute("orderList",list);

        List<Product> list1 = productService.listAllProducts();
        m.addAttribute("productList",list1);

        return "admin/order-detail/edit";
    }

    @PostMapping(value="/update")
    public String update(@ModelAttribute("orderDetail") OrderDetail orderDetail){
        orderDetailService.updateOrderDetail(orderDetail);
        return "redirect:/order-detail/list";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        orderDetailService.deleteOrderDetail(id);
        return "redirect:/order-detail/list";
    }
}
