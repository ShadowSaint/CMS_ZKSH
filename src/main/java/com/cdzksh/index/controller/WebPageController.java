package com.cdzksh.index.controller;

import com.cdzksh.index.domain.ArticleDO;
import com.cdzksh.index.domain.ArticleQueryParam;
import com.cdzksh.index.domain.ArticleVOIndex;
import com.cdzksh.index.domain.MenuDO;
import com.cdzksh.index.service.ArticleService;
import com.cdzksh.index.service.MenuService;
import com.cdzksh.index.util.GRQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Created by ShadowSaint on 2018/7/1
 */
@Controller
public class WebPageController {
    @Autowired
    ArticleService articleService;
    @Autowired
    MenuService menuService;

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        //轮播图
        List<ArticleDO> lbtArticleList = articleService.listCarouselArticle();
        //商会公告
        List<ArticleDO> shggArticleList = articleService.listArticleInfoByParam(new ArticleQueryParam(21), 1, 8);
        //商会新闻
        List<ArticleVOIndex> shxwArticleList = articleService.listArticleVOByParam(new ArticleQueryParam(2), 1, 6);
        //政策法规
        List<ArticleVOIndex> zcfgArticleList = articleService.listArticleVOByParam(new ArticleQueryParam(12), 1, 6);
        //防水之窗
        List<ArticleVOIndex> fszcArticleList = articleService.listArticleVOByParam(new ArticleQueryParam(13), 1, 6);
        //周蓉聚焦
        List<ArticleVOIndex> rzjjArticleList = articleService.listArticleVOByParam(new ArticleQueryParam(14), 1, 6);
        //商会交流
        List<ArticleVOIndex> shjlArticleList = articleService.listArticleVOByParam(new ArticleQueryParam(15), 1, 6);
        //法律服务
        List<ArticleVOIndex> flfwArticleList = articleService.listArticleVOByParam(new ArticleQueryParam(16), 1, 6);

        modelMap.addAttribute("lbt", lbtArticleList);
        modelMap.addAttribute("shgg", shggArticleList);
        modelMap.addAttribute("shxw", shxwArticleList);
        modelMap.addAttribute("zcfg", zcfgArticleList);
        modelMap.addAttribute("fszc", fszcArticleList);
        modelMap.addAttribute("rzjj", rzjjArticleList);
        modelMap.addAttribute("shjl", shjlArticleList);
        modelMap.addAttribute("flfw", flfwArticleList);
        modelMap.addAttribute("menu_id", 0);
        modelMap.addAttribute("menu_list", MenuController.getTopMenu());
        return "index";
    }

    @RequestMapping("/news/list")
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int menu_id = GRQUtil.getRequestInteger(request, "menu_id", 2);
        int page = GRQUtil.getRequestInteger(request, "page", 1);
        int size = GRQUtil.getRequestInteger(request,"size",20);

        Page<ArticleDO> articleDOList = articleService.listArticleInfoByParam(new ArticleQueryParam(menu_id), page, size);
        for (ArticleDO articleDO:articleDOList){
            articleDO.setGmt_create(LocalDateTime.parse(articleDO.getGmt_create(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }
        List<MenuDO> menuDOList = menuService.listMenu("0", "1");
        Map<MenuDO, List<ArticleDO>> map = new HashMap<>();
        for (MenuDO menuDO : menuDOList) {
            if (!menuDO.getId().equals(menu_id)) {
                List<ArticleDO> menuArticleList = articleService.listArticleInfoByParam(new ArticleQueryParam(menuDO.getId()), 1, 5);
                map.put(menuDO,menuArticleList);
            }
        }

        int total= (int) articleDOList.getTotal();
        int maxPage=total/size;
        if (total%size>0){
            maxPage++;
        }
        modelMap.addAttribute("list",articleDOList);
        modelMap.addAttribute("map",map);
        modelMap.addAttribute("max_page",maxPage);
        modelMap.addAttribute("now_page",page);
        modelMap.addAttribute("menu_id", menu_id);
        modelMap.addAttribute("menu_list", MenuController.getTopMenu());
        return "list";
    }

    //带左侧菜单的详情页面
    //商会成员
    @RequestMapping("/c_member")
    public String cMember() {
        return "c_member";
    }

    //商会章程
    @RequestMapping("/menu/detail")
    public String cInfo(HttpServletRequest request,ModelMap modelMap) {
        int menu_id = GRQUtil.getRequestInteger(request, "id", 2);
        MenuDO menuDO=menuService.getMenu(menu_id);
        List<MenuDO> menuDOList=menuService.listMenu("0","0");

        modelMap.addAttribute("menu",menuDO);
        modelMap.addAttribute("left_menu_list",menuDOList);
        modelMap.addAttribute("menu_id", menu_id);
        modelMap.addAttribute("menu_list", MenuController.getTopMenu());
        return "c_info";
    }

    //商会活动
    @RequestMapping("/c_active")
    public String cActive() {
        return "c_active";
    }

    //商会简介
    @RequestMapping("/c_about")
    public String cBout() {
        return "c_about";
    }

    //关于我们
    @RequestMapping("/about_us")
    public String aboutUs() {
        return "about_us";
    }

    //图片新闻（特区河南）
    @RequestMapping("/picNewsList")
    public String picNewsList() {
        return "pic_list";
    }

    //新闻详情页面
    @RequestMapping("/news/detail")
    public String newsDetail(HttpServletRequest request,ModelMap modelMap) {
        int id=GRQUtil.getRequestInteger(request,"id",0);
        ArticleDO articleDO=articleService.getArticleById(id);
        int menu_id = GRQUtil.getRequestInteger(request, "menu_id", 2);
        List<MenuDO> menuDOList = menuService.listMenu("0", "1");
        Map<MenuDO, List<ArticleDO>> map = new HashMap<>();
        for (MenuDO menuDO : menuDOList) {
            if (!menuDO.getId().equals(menu_id)) {
                List<ArticleDO> menuArticleList = articleService.listArticleInfoByParam(new ArticleQueryParam(menuDO.getId()), 1, 5);
                map.put(menuDO,menuArticleList);
            }
        }
        modelMap.addAttribute("article",articleDO);
        modelMap.addAttribute("map",map);
        modelMap.addAttribute("menu_id", menu_id);
        modelMap.addAttribute("menu_list", MenuController.getTopMenu());
        return "news_detail";
    }

    //后台管理详情页面
    @RequestMapping("/manage/article")
    public String manageArticle() {
        return "manage_article";
    }

    //添加文章页面
    @RequestMapping("/manage/add_article")
    public String addArticle() {
        return "manage_add_article";
    }

    //添加文章页面
    @RequestMapping("/manage/add_menu")
    public String addMenu() {
        return "manage_add_menu";
    }

}
