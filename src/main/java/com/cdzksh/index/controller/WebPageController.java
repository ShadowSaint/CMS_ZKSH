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
    @RequestMapping("/about_us")
    public String aboutUs(){
        return "about_us";
    }
    //图片新闻（特区湖南）
    @RequestMapping("/picNewsList")
    public String picNewsList(){
        return "pic_list";
    }
    //新闻详情页面
    @RequestMapping("/newsDetail")
    public String newsDetail(){
        return "news_detail";
    }
}
