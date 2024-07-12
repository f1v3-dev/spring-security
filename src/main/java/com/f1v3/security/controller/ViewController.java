package com.f1v3.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        String authority = authentication.getAuthorities().iterator().next().getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("authority", authority);

        return "dashboard";
    }

    @GetMapping("/setting/admin")
    public String adminSettingPage() {
        return "admin_setting";
    }

    @GetMapping("/setting/user")
    public String userSettingPage() {
        return "user_setting";
    }
}
