package com.cdzksh.index.controller;

import com.cdzksh.index.domain.MenuDO;
import com.cdzksh.index.domain.ResultVO;
import com.cdzksh.index.service.MenuService;
import com.cdzksh.index.util.GRQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/api/manege/menu/insert")
    public ResultVO apiManageMenuInsert(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int p_id = GRQUtil.getRequestInteger(request, "p_id", 0);
            String name = request.getParameter("name");
            int seq = GRQUtil.getRequestInteger(request, "seq", 0);

            MenuDO menuDO = new MenuDO();
            menuDO.setGmt_create(now);
            menuDO.setGmt_modified(now);
            menuDO.setP_id(p_id);
            menuDO.setName(name);
            menuDO.setSeq(seq);

            menuService.insertMenu(menuDO);

        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/menu/delete")
    public ResultVO apiManageMenuDelete(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            int id = Integer.valueOf(request.getParameter("id"));

            menuService.deleteMenu(id);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/menu/update")
    public ResultVO apiManageMenuUpdate(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int p_id = GRQUtil.getRequestInteger(request, "p_id", 0);
            String name = request.getParameter("name");
            int seq = GRQUtil.getRequestInteger(request, "seq", 0);

            MenuDO menuDO = new MenuDO();
            menuDO.setId(id);
            menuDO.setGmt_modified(now);
            menuDO.setP_id(p_id);
            menuDO.setName(name);
            menuDO.setSeq(seq);

            menuService.updateMenu(menuDO);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/menu/list")
    public ResultVO apiManageMenuList(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            int p_id = GRQUtil.getRequestInteger(request, "p_id", 0);

            List<MenuDO> list = menuService.listMenu(p_id);

            resultVO.setData(list);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }
}
