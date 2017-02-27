package com.linkwee.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.UserMapper;
import com.linkwee.web.model.User;
import com.linkwee.test.TestSupport;

public class UserMapperTest extends TestSupport {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test_selectByExampleAndPage() {
        start();
        Page<User> page = new Page<User>(1, 3);
        final List<User> users = userMapper.selectListByUsername("k", page);
        for (User user : users) {
            System.err.println(user);
        }
        end();
    }
}
