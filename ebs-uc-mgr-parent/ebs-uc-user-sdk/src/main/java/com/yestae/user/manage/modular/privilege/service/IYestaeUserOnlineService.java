package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserOnline;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-07
 */
public interface IYestaeUserOnlineService extends IService<YestaeUserOnline> {

	/**
	 * 获取在线用户列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeUserOnlineList(Page<Map<String, Object>> page, Map<String, String> map);
	
}
