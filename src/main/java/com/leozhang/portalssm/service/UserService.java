package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Menu;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.util.Result;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    String getUserList();

    String getMenuListForPage(int pno, int psize);

    List<Menu> testList(int pno, int psize, Model model);

    User findByUsername(String userName);

    User login(String username, String password, HttpServletRequest request);

    Result getListForPage(int pno, int psize, String username, String sortField, String sortType);

    void insertUser(User user);

    User findUserById(Long id);

    void editUser(User user);

    void deleteUserById(Long id);

    String changePassword(HttpSession session, User user, Model model);
}
