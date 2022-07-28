package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Menu;
import com.leozhang.portalssm.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @GetMapping("/test")
    public String test(Model model, @RequestParam(value = "pno",defaultValue = "1")int pno,
                       @RequestParam(value = "psize",defaultValue = "10")int psize){
        List<Menu> list = userService.testList(pno,psize,model);

        return "test";
    }


    @GetMapping("/user/list")
    @ResponseBody
    public String getUserList(){
        return userService.getUserList();
    }


    @GetMapping("/menu/list/page")
    @ResponseBody
    public String getMenuListForPage(
            @RequestParam(value = "pno",defaultValue = "1")int pno,
            @RequestParam(value = "psize",defaultValue = "10")int psize
    ){
        return userService.getMenuListForPage(pno,psize);
    }
}
