package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.UserRealNameAuthenticationMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.UserRealNameAuthentication;
import com.yestae.user.manage.modular.privilege.service.IUserRealNameAuthenticationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 实名认证 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-03-23
 */
@Service
public class UserRealNameAuthenticationServiceImpl extends ServiceImpl<UserRealNameAuthenticationMapper, UserRealNameAuthentication> implements IUserRealNameAuthenticationService {

	@Resource
	UserRealNameAuthenticationMapper userRealNameAuthenticationMapper;
	
	
	@Override
	public int selectCountByIdNo(String idNo, String idType) {
		return userRealNameAuthenticationMapper.selectCountByIdNo(idNo, idType);
	}


	@Override
	public List<Map<String, Object>> selectUserRealNameAuthenticationList(Page<Map<String, Object>> page,
																		  Map<String, String> paramMap) {
		paramMap.put("ifDel", SysEnum.NO.getCode() + "");
		return userRealNameAuthenticationMapper.selectUserRealNameAuthenticationList(page, paramMap);
	}
	
}
