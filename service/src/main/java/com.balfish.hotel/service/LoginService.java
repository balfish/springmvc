package com.balfish.hotel.service;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

@Service("loginService")
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    private static final int TIMEOUT = 24 * 60 * 60;

    private static final String COOKIE_USERNAME = "login_username";
    private static final String COOKIE_TIME = "login_time";
    private static final String COOKIE_TOKEN = "login_token";

    public LoginService() {
    }

    private String getCookieToken(final String username,
                                  final String time) {
        return Hashing.md5().hashString(username + "_" + time, Charsets.UTF_8).toString();
    }

    public void login(final String username,
                      final HttpServletResponse resp) {
        String currentTime = String.valueOf(System.currentTimeMillis());

        Cookie user = new Cookie(COOKIE_USERNAME, username);
        Cookie time = new Cookie(COOKIE_TIME, currentTime);
        Cookie token = new Cookie(COOKIE_TOKEN, getCookieToken(username, currentTime));

        user.setPath("/");
        time.setPath("/");
        token.setPath("/");

        user.setMaxAge(TIMEOUT);
        time.setMaxAge(TIMEOUT);
        token.setMaxAge(TIMEOUT);

        resp.addCookie(user);
        resp.addCookie(time);
        resp.addCookie(token);
    }

    public void logout(HttpServletResponse resp) {
        Cookie user = new Cookie(COOKIE_USERNAME, null);
        Cookie time = new Cookie(COOKIE_TIME, null);
        Cookie token = new Cookie(COOKIE_TOKEN, null);

        user.setPath("/");
        time.setPath("/");
        token.setPath("/");

        user.setMaxAge(0);
        time.setMaxAge(0);
        token.setMaxAge(0);

        resp.addCookie(user);
        resp.addCookie(time);
        resp.addCookie(token);
    }

    public boolean isLogin(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return false;
        }
        String username = null;
        String time = null;
        String token = null;

        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (Objects.equals(name, COOKIE_USERNAME)) {
                username = cookie.getValue();
            } else if (Objects.equals(name, COOKIE_TIME)) {
                time = cookie.getValue();
            } else if (Objects.equals(name, COOKIE_TOKEN)) {
                token = cookie.getValue();
            }
        }
        LOGGER.debug("username = {}, time = {}, token = {}", username, time, token);
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(time) || Strings.isNullOrEmpty(token)) {
            return false;
        }
        return Objects.equals(token, getCookieToken(username, time));
    }

    public String current(final HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (Objects.equals(COOKIE_USERNAME, cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}