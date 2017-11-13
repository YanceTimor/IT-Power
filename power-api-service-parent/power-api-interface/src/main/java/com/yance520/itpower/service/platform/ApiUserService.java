package com.yance520.itpower.service.platform;

import com.yance520.itpower.model.paltform.User;

/**
 * Author : 杨杨
 * Date : 2017/08/22
 * Description :
 */
public interface ApiUserService {

    User checkUser(String userName);

    User login(String userName,String password);

}
