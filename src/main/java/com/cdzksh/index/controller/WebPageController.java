package com.cdzksh.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Created by ShadowSaint on 2018/7/1
 */
@Controller
public class WebPageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/list")
    public String list(){
        return "list";
    }
    //带左侧菜单的详情页面
    //商会成员
    @RequestMapping("/c_member")
    public String cMember(){
        return "c_member";
    }
    //商会章程
    @RequestMapping("/c_info")
    public String cInfo(){
        return "c_info";
    }
    //商会活动
    @RequestMapping("/c_active")
    public String cActive(){
        return "c_active";
    }
    //商会简介
    @RequestMapping("/c_about")
    public String cBout(){
        return "c_about";
    }
    //关于我们
    @RequestMapping("/about_us")
    public String aboutUs(){
        return "about_us";
    }
    //图片新闻（特区河南）
    @RequestMapping("/picNewsList")
    public String picNewsList(){
        return "pic_list";
    }
    //新闻详情页面
    @RequestMapping("/newsDetail")
    public String newsDetail(){
        return "news_detail";
    }
    //后台管理详情页面
    @RequestMapping("/manage_article")
    public String manageArticle(){
        return "manage_article";
    }
    //添加文章页面
    @RequestMapping("/manage_add_article")
    public String addArticle(){
        return "manage_add_article";
    }
    //添加文章页面
    @RequestMapping("/manage_add_menu")
    public String addMenu(){
        return "manage_add_menu";
    }

}
