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
    @RequestMapping("/template")
    public String template(){
        return "template";
    }
    @RequestMapping("/header")
    public String header(){
        return "header";
    }
}
