package com.yestae.user.manage.modular.privilege.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccountFlow;

/**
 * <p>
 * 账户资金变动流水 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
public interface IUserAccountFlowService extends IService<UserAccountFlow> {

	List<Map<String, Object>> selectUserAccountFlowList(Page<Map<String, Object>> page, Map<String, String> map);
	
}
