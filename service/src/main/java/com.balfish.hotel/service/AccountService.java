/*
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.balfish.hotel.service;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.springframework.stereotype.Service;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

@Service("accountService")
public class AccountService {

    private final Properties accounts = new Properties();

    public AccountService() {
    }

    public AccountService(final String resource) {
        try {
            accounts.load(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            throw new IllegalArgumentException("无法读取账号信息,账号文件为:" + resource, e);
        }
    }

    public boolean containsAccount(String username) {
        return accounts.containsKey(username);
    }

    public String getPassword(String username) {
        return accounts.getProperty(username);
    }

}
