package com.balfish.hotel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

@Service("cookieService")
public class CookieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Object.class);

    @Resource
    private AccountService accountService ;

    @Resource
    private LoginService loginService;

    public boolean login(String username, String password, HttpServletResponse resp, Model model) {

        LOGGER.debug("accountContainer.containsAccount(username) return {}", accountService.containsAccount(username));
        LOGGER.debug("Objects.equals(password, accountContainer.getPassword(username)) return {}", Objects.equals(password, accountService.getPassword(username)));

        if (!accountService.containsAccount(username) || !Objects.equals(password, accountService.getPassword(username))) {
            model.addAttribute("message", "登录账户密码不正确");
            return false;
        }
        loginService.login(username, resp);
        return true;
    }

    public void logout(HttpServletResponse resp) {
        loginService.logout(resp);
    }

    public boolean isLogin(HttpServletRequest req) {
        return loginService.isLogin(req);
    }
}
