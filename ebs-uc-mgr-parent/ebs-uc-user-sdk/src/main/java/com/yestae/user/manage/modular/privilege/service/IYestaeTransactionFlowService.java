package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeTransactionFlow;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  用户消费流水服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-16
 */
public interface IYestaeTransactionFlowService extends IService<YestaeTransactionFlow> {

	/**
	 * 用户消费流水查询
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeTransactionFlowList(Page<Map<String, Object>> page, Map<String, String> map);
	
}
