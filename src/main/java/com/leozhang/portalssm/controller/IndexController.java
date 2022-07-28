package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Menu;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
//        User user = (User) session.getAttribute("userInfo");
//        List<Menu> menuList = menuService.selectMenuListByRoleId(user.getRoleId());
//        session.setAttribute("menuList",menuList);
        return "index";
    }
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
