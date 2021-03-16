package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyGradeLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户会员等级变更日志 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface IYestaeUserModifyGradeLogService extends IService<YestaeUserModifyGradeLog> {

	/**
	 * 查询用户等级变更日志列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeUserModifyGradeLogList(Page<Map<String, Object>> page,
																 Map<String, String> map);
	
}
