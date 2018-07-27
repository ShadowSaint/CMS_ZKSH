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
        if (username.equals("rzshadmin") && (password.equals("rzsh") || password.toLowerCase().equals("3a106092635945849a0fbf7bac92409d"))) {
            session.setAttribute("User", "admin");
            return "redirect:/manage/article?type=2";
        } else {
            return "redirect:/login?msg=wrong";
        }
    }

    @RequestMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
