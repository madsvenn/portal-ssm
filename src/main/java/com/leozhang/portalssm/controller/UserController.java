package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Role;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.service.RoleService;
import com.leozhang.portalssm.service.UserService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;


    @RequestMapping("/list")
    @RequiresPermissions(value = {"permission:query"})
    public String userList(

            @RequestParam(value = "username",defaultValue = "") String username,
            Model model
    ){
        model.addAttribute("username",username);
        return "system/user/list";
    }


    @RequestMapping("/list/page")
    @RequiresPermissions("permission:query")
    @ResponseBody
    public Result getUserListForPage(
            @RequestParam(value = "pno",defaultValue = "1") int pno,
            @RequestParam(value = "psize",defaultValue = "10") int psize,
            @RequestParam(value = "username",defaultValue = "") String username,
            @RequestParam(value = "sortField",defaultValue = "") String sortField,
            @RequestParam(value = "sortType",defaultValue = "") String sortType

    ){
        return userService.getListForPage(pno,psize,username,sortField,sortType);
    }


    @RequestMapping("/add/page")
    public String userAddPage(Model model){

        List<Role> roleList = roleService.selectRoleListAll();
        model.addAttribute("roleList",roleList);
        return "system/user/add";
    }
    @RequestMapping("/edit/page")
    public String userEditPage(Long id,Model model){
        User user = userService.findUserById(id);
        List<Role> roleList = roleService.selectRoleListAll();
        model.addAttribute("formData",user);
        model.addAttribute("roleList",roleList);
        return "system/user/edit";
    }

    @RequestMapping("/add")
    public String userAdd(User user){
        System.out.println(user);
        userService.insertUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/edit")
    public String userEdit(User user){
        System.out.println(user);
        userService.editUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/delete")
    public String userDelete(Long id){
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/password/page")
    public String userPasswordPage(HttpSession session,Model model){
        User user = (User)session.getAttribute("userInfo");
        user.setPassword("");
        model.addAttribute("formData",user);
        return "system/password/password";
    }

    @RequestMapping("/password/change")
    public String userPasswordChange(HttpSession session,Model model,User user){
        return userService.changePassword(session,user,model);
    }
}
