package com.yestae.user.manage.modular.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.system.persistence.dao.UserMapper;
import com.yestae.user.manage.modular.system.persistence.model.User;
import com.yestae.user.manage.modular.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

	@Override
	@DataSource(name="dataSourceUcMgr")
	public User selectUserById(Serializable id) {
		return this.getById(id);
	}


}
