package com.yestae.user.manage.modular.privilege.service;

import com.yestae.user.manage.modular.privilege.persistence.model.UserRealNameAuthentication;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 实名认证 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-03-23
 */
public interface IUserRealNameAuthenticationService extends IService<UserRealNameAuthentication> {

	/**
	 * 根据身份证号查询认证次数
	 * @param wrapper
	 * @return
	 */
	int selectCountByIdNo(String idNo, String idType);

	/**
	 * 查询实名认证列表
	 * @param page
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> selectUserRealNameAuthenticationList(Page<Map<String, Object>> page,
			Map<String, String> map);
	
}
