package com.aurosoft.ecommerce.controller;


import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.CompanyDetail;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.UserService;
import com.aurosoft.ecommerce.util.Helper;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Controller

public class LoginController {

    @Autowired
    private UserService userService;

    public LoginController(UserService userService)
    {
        this.userService = userService;
    }



    @GetMapping(value= "/profile")
    public String profile(Model m ,  HttpSession session){

        if(!Helper.checkUserRole() && !Helper.checkAdminRole()){
            return "redirect:/login ";
        }

        int uid=0;
        if(session.getAttribute("uid") != null) {
            uid = (int)session.getAttribute("uid");
        }
        User user = userService.getUserById(uid);
        m.addAttribute("user",user);

        if(Helper.checkUserRole()){
            return "user/profile";
        }
      else {
          return "login/profile";
        }
    }


    @GetMapping(value= "/editprofile")
    public String editProfile(Model m, HttpSession session) {

        if(!Helper.checkUserRole() && !Helper.checkAdminRole()){
            return "redirect:/login ";
        }

        int uid=0;
        if(session.getAttribute("uid") != null) {
            uid = (int)session.getAttribute("uid");
        }
        User user = userService.getUserById(uid);
        m.addAttribute("user",user);

        if (Helper.checkUserRole()){
            return "user/edit-profile";
        }
        else {
            return "login/edit-profile";
        }

    }

    @PostMapping(value = "/update-profile")
    public String updateProfile(@ModelAttribute("user")User user, HttpSession session) {

        User user2 =  userService.getUserById(user.getId());

        user2.setFname(user.getFname());
        user2.setLname(user.getLname());
        user2.setCity(user.getCity());
        user2.setMobile(user.getMobile());



        userService.updateUser(user2);
        session.setAttribute("msg", "Your Profile is successfully updated..");
        return "redirect:/profile";
    }


    @GetMapping("/signup")
    public String signup(User user) {
        return "signup";
    }

    @PostMapping("/new-signup")
    public String newSignup(@ModelAttribute("user")User user,HttpSession session) {

        userService.insertUser(user);
        session.setAttribute("msg", "Sign up successfully!! Now you can login..");
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword()
    {
        return "forgot-password";
    }

    @PostMapping("/update-forgot-password")
    public String updateForgotPassword(@RequestParam("email") String email , HttpSession session)
    {
        User user = userService.findByEmail(email);

        if (user != null) {
            session.setAttribute("email",email);
            return "redirect:/reset-password";

        } else {

            session.setAttribute("msg", "Something Went Wrong..");
            return "redirect:/forgot-password";
        }
    }
    @GetMapping("/reset-password")
    public String resetPassword()
    {
        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("password") String password,
                                @RequestParam("cpassword") String cpassword,
                                HttpSession session){

        if(password.equals(cpassword)){
            String email = session.getAttribute("email" ).toString();
            User user = userService.findByEmail(email);
            user.setPassword(password);
            user = userService.updateUser(user);
            if (user!= null){
                session.setAttribute("msg","Password Reset");
                return "redirect:/login";
            }
            else {
                session.setAttribute("msg","Something went wrong!!");
                return "redirect:/reset-password";
            }
        }else {
            session.setAttribute("msg","Password and Confirm password not match");
            return "redirect:/reset-password";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(HttpSession session) {
        if(!Helper.checkUserRole() && !Helper.checkAdminRole()){
            return "redirect:/login ";
        }
        if (Helper.checkUserRole()){
            return "user/change-password";
        }
        else {
            return "login/change-password";
        }
    }

    @PostMapping("/update-change-password")
    public String updateChangePassword(@RequestParam("oldpassword") String oldpassword,
                                       @RequestParam("password") String password,
                                       @RequestParam("cpassword") String cpassword,
                                       HttpSession session){

        if(!Helper.checkUserRole() && !Helper.checkAdminRole()){
            return "redirect:/login ";
        }

        String email = session.getAttribute("email" ).toString();
        User user = userService.findByEmail(email);

        if (!user.getPassword().equals(oldpassword)){
            session.setAttribute("msg","Old Password is not matched");
            return "redirect:/change-password";
        }

        if(password.equals(cpassword)){

            user.setPassword(password);
            user = userService.updateUser(user);
            if (user!= null){
                session.setAttribute("msg","Password Reset Successfully");
                return "redirect:/profile";
            }
            else {
                session.setAttribute("msg","Something went wrong!!");
                return "redirect:/reset-password";
            }
        }else {
            session.setAttribute("msg","Password and Confirm password is not matched");
            return "redirect:/reset-password";
        }

    }






}
