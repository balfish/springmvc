package com.balfish.hotel.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

@Controller
public class HomeController {

    @RequestMapping("/homePage")
    public String homePage(final Model model,
                           final HttpServletRequest req) {

        String username = null;
        if (StringUtils.isNotBlank(req.getParameter("username"))) {
            username = req.getParameter("username");
        }

        if (StringUtils.isNotBlank(username)) {
            model.addAttribute("username", req.getParameter("username"));
        } else {
            model.addAttribute("username", "visitor");
        }
        return "homePage";
    }
}
