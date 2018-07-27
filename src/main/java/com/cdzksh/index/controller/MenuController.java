package com.cdzksh.index.controller;

import com.cdzksh.index.domain.MenuDO;
import com.cdzksh.index.domain.MenuVO;
import com.cdzksh.index.domain.ResultVO;
import com.cdzksh.index.service.MenuService;
import com.cdzksh.index.util.GRQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    public static List<MenuVO> getTopMenu(){
        List<MenuVO> list=new ArrayList<>();
        list.add(new MenuVO(0,"首页","/"));
        list.add(new MenuVO(22,"商会简介","/menu/detail?id=22"));
        list.add(new MenuVO(2,"商会新闻","/news/list?id=2"));
        list.add(new MenuVO(6,"商会章程","/menu/detail?id=6"));
        list.add(new MenuVO(10,"商会会员","/menu/detail?id=10"));
        list.add(new MenuVO(11,"互助基金","/menu/detail?id=11"));
        return list;
    }

    @RequestMapping("/api/manege/menu/insert")
    public ResultVO apiManageMenuInsert(HttpServletRequest request) {
        ResultVO resultVO = new ResultVO();
        try {
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int p_id = GRQUtil.getRequestInteger(request, "p_id", 0);
            String name = request.getParameter("name");
            int seq = GRQUtil.getRequestInteger(request, "seq", 0);
            String content=request.getParameter("content");
            int type=GRQUtil.getRequestInteger(request,"type",0);

            MenuDO menuDO = new MenuDO();
            menuDO.setGmt_create(now);
            menuDO.setGmt_modified(now);
            menuDO.setP_id(p_id);
            menuDO.setName(name);
            menuDO.setSeq(seq);
            menuDO.setContent(content);
            menuDO.setType(type);

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
            String content=request.getParameter("content");
            int type=GRQUtil.getRequestInteger(request,"type",0);

            MenuDO menuDO = new MenuDO();
            menuDO.setId(id);
            menuDO.setGmt_modified(now);
            menuDO.setP_id(p_id);
            menuDO.setName(name);
            menuDO.setSeq(seq);
            menuDO.setContent(content);
            menuDO.setType(type);

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
            String p_id = request.getParameter("p_id");
            String type = request.getParameter("type");

            List<MenuDO> list = menuService.listMenu(p_id,type);

            resultVO.setData(list);
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }

    @RequestMapping("/api/manage/menu")
    public ResultVO apiManageMenu(HttpServletRequest request){
        ResultVO resultVO=new ResultVO();
        try {
            int id=Integer.valueOf(request.getParameter("id"));

            MenuDO menuDO=menuService.getMenu(id);

            resultVO.setData(menuDO);

        }catch (Exception e){
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;
    }
}
