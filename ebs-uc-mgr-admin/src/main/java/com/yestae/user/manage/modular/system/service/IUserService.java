package com.yestae.user.manage.modular.system.service;

import java.io.Serializable;

import com.baomidou.mybatisplus.service.IService;
import com.yestae.user.manage.modular.system.persistence.model.User;

public interface IUserService extends IService<User>{

	User selectUserById(Serializable id); 
}
