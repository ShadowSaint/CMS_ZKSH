package com.cdzksh.index.service;

import com.cdzksh.index.domain.MenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
public interface MenuService {
    void insertMenu(@Param("m")MenuDO menuDO);
    void deleteMenu(int id);
    void updateMenu(@Param("m")MenuDO menuDO);
    List<MenuDO> listMenu(int p_id);

}
