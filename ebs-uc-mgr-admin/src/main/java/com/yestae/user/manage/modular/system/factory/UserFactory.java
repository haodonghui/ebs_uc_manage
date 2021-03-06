package com.yestae.user.manage.modular.system.factory;

import org.springframework.beans.BeanUtils;

import com.yestae.user.manage.modular.system.persistence.model.User;
import com.yestae.user.manage.modular.system.transfer.UserDto;

/**
 * 用户创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}
