package com.lldragon.controller;

import com.lldragon.entiy.User;
import com.lldragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, HttpSession session){
        User userDB = userService.login(user);
        System.out.println(userDB);
        if(userDB!=null){
            session.setAttribute("user",userDB);
            return "success";
        }else{
            return "fail";
        }
    }


//    @RequestMapping("/login")
//    public String login(User user, HttpSession session){
//        User userDB = userService.login(user);
//        if(userDB!=null){
//            session.setAttribute("user",userDB);
//            return "redirect:/file/showAll";
//        }else{
//            return "redirect:/index";
//        }
//    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(User user){
        System.out.println(user);
        userService.register(user);
        return "/index";

    }
    @RequestMapping(value = "/checkName")
    @ResponseBody
    public String checkName(@RequestParam("username") String username){
        System.out.println("username======>"+username);
         boolean flag = userService.existName(username);
         if(flag){
             return "exist";
         }else{
             return "success";
         }
    }
}
