package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyPhoneLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户手机号变更日志 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface IYestaeUserModifyPhoneLogService extends IService<YestaeUserModifyPhoneLog> {

	/**
	 * 查询用户手机号修改日志
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeUserModifyPhoneLogList(Page<Map<String, Object>> page,
																 Map<String, String> map);
	
	void dealList(List<Map<String, Object>> list);
}
