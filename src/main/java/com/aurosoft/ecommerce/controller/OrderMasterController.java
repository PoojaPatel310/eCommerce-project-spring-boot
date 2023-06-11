package com.aurosoft.ecommerce.controller;

import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.OrderMaster;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.OrderMasterService;
import com.aurosoft.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order-master")
public class OrderMasterController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private UserService userService;

    public OrderMasterController(OrderMasterService orderMasterService, UserService userService) {
        this.orderMasterService = orderMasterService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listAll(Model m){
        List<OrderMaster> list= orderMasterService.listAllOrderMasters();
        m.addAttribute("list",list);
        return "admin/order-master/list";
    }

    @GetMapping("/new")
    public String showForm(OrderMaster orderMaster,Model m){

        List<User> list = userService.listAllUsers();
        m.addAttribute("userList",list);

        return "admin/order-master/add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute("orderMaster") OrderMaster orderMaster){
        orderMaster.setOrderDate(new Date());
        orderMasterService.insertOrderMaster(orderMaster);
        return "redirect:/order-master/list";
    }
    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        OrderMaster orderMaster = orderMasterService.getOrderMasterById(id);
        m.addAttribute("orderMaster",orderMaster);

        List<User> list = userService.listAllUsers();
        m.addAttribute("userList",list);

        return "admin/order-master/edit";
    }

    @PostMapping(value="/update")
    public String update(@ModelAttribute("orderMaster") OrderMaster orderMaster){
        orderMaster.setOrderDate(new Date());
        orderMasterService.updateOrderMaster(orderMaster);
        return "redirect:/order-master/list";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id){
        orderMasterService.deleteOrderMaster(id);
        return "redirect:/order-master/list";
    }
    
}
