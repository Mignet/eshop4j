package com.linkwee.test.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.core.security.PasswordHash;
import com.linkwee.web.model.User;
import com.linkwee.web.service.UserService;
import com.linkwee.test.TestSupport;

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
