package com.eshop4j.test.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.eshop4j.core.security.PasswordHash;
import com.eshop4j.web.model.User;
import com.eshop4j.web.service.UserService;
import com.eshop4j.test.TestSupport;

public class UserServiceTest extends TestSupport {

    @Resource
    private UserService userService;

    @Test
    public void testInsert() throws NoSuchAlgorithmException, InvalidKeySpecException {
    	start();
            User model = new User("Mignet",PasswordHash.createHash("123456"));
            model.setCreateTime(new Date());
            userService.insert(model);
        end();
    }
}
