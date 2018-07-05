package com.cdzksh.index.service.impl;

import com.cdzksh.index.dao.MenuMapper;
import com.cdzksh.index.domain.MenuDO;
import com.cdzksh.index.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    MenuMapper menuMapper;

    @Override
    public void insertMenu(MenuDO menuDO) {
        menuMapper.insertMenu(menuDO);
    }

    @Override
    public void deleteMenu(int id) {
        menuMapper.deleteMenu(id);
    }

    @Override
    public void updateMenu(MenuDO menuDO) {
        menuMapper.updateMenu(menuDO);
    }

    @Override
    public List<MenuDO> listMenu(int p_id) {
        return menuMapper.listMenu(p_id);
    }
}
