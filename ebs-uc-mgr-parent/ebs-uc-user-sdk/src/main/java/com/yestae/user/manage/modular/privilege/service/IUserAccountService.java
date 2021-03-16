package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccount;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账户信息 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
public interface IUserAccountService extends IService<UserAccount> {

	List<Map<String, Object>> selectUserAccountList(Page<Map<String, Object>> page, Map<String, String> map);

	/**
	 * 余额修改
	 * @param map
	 *        必须参数：id，type，amount，remark，updateTime
	 * @return
	 */
	int updateUserAccount(Map<String, String> map);
	
}
