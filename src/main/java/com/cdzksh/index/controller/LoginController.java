package com.cdzksh.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
@Controller
public class LoginController {

    @RequestMapping("**/login_redirect_long_string_just_in_case")
    public String redirectLoginPage() {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        if (username == null) {
            return "login";
        }
        String password = request.getParameter("password");
        if (username.equals("zkshadmin") && (password.equals("zksh") || password.toLowerCase().equals("4717273d97c5d0cbcd37c19e9f2585f3"))) {
            session.setAttribute("User", "admin");
            return "redirect:/manage/index";
        } else {
            return "redirect:/login?msg=wrong";
        }
    }

    @RequestMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
