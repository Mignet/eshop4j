package com.eshop4j.web.service;

import com.eshop4j.core.generic.GenericService;
import com.eshop4j.web.model.User;

/**
 * 用户 业务 接口
 * 
 * @author Mignet
 * @since 2014年7月5日 上午11:53:33
 **/
public interface UserService extends GenericService<User, Integer> {

    /**
     * 用户验证
     * 
     * @param user
     * @return
     */
    User authentication(User user);

}
