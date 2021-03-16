package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccountFlow;

import java.util.List;
import java.util.Map;

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
