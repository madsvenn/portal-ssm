package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Menu;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.service.MenuService;
import com.leozhang.portalssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;


    @RequestMapping("/page")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpServletRequest request, Model m){
        User u = userService.login(username,password,request);

        System.err.println(u);
        if(u == null){
            m.addAttribute("success",false);
            return "login";

        }else{
            List<Menu> menuList = menuService.selectMenuListByRoleId(u.getRoleId());
            HttpSession session = request.getSession();
            session.setAttribute("menuList",menuList);
            m.addAttribute("success",true);
            return "redirect:/index";

        }

    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login/page";
    }
}
