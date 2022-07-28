package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Menu;
import com.leozhang.portalssm.util.Result;

import java.util.List;

public interface MenuService {
    List<Menu> selectMenuListByRoleId(Long roleId);

    List<Menu> selectMenuTree();

    Result selectMenuTreeForObject();

    Result selectMenuTreeById(Long id);

    void insertMenu(Menu menu);

    void deleteMenuById(Long id);

    Menu selectMenuById(Long id);

    void updateMenuById(Menu menu);

    void insertMenuChild(Menu menu);
}
