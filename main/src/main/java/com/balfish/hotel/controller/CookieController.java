package com.balfish.hotel.controller;

import com.balfish.hotel.service.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

@Controller
@RequestMapping("/cookie")
public class CookieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieController.class);

    @Resource
    private CookieService cookieService;

    @RequestMapping(value = "/login")
    public String login() {
        return "cookie/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(final String username,
                          final String password,
                          final HttpServletResponse resp,
                          final Model model) {

        LOGGER.info("username: {}, password: {}", username, password);
        if (cookieService.login(username, password, resp, model)) {
            model.addAttribute("username", username);
            return "redirect:/homePage";
        }
        return "cookie/login";
    }

    @RequestMapping("/logout")
    public String logout(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        if (cookieService.isLogin(req)) {
            cookieService.logout(resp);
        }
        return "redirect:/homePage";
    }
}
