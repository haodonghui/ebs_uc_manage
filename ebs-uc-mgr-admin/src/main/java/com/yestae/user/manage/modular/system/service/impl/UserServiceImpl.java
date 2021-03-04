package com.yestae.user.manage.modular.system.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.system.persistence.dao.UserMapper;
import com.yestae.user.manage.modular.system.persistence.model.User;
import com.yestae.user.manage.modular.system.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

	@Override
	@DataSource(name="dataSourceUcMgr")
	public User selectUserById(Serializable id) {
		return this.selectById(id);
	}


}
