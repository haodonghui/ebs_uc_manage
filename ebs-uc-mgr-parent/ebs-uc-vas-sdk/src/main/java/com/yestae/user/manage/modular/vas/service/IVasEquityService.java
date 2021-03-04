package com.yestae.user.manage.modular.vas.service;

import com.yestae.user.manage.modular.vas.persistence.model.VasEquity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 增值服务权益关联表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
public interface IVasEquityService extends IService<VasEquity> {

	/**
	 * 查询增值服务-权益列表
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> selectVasEquityList(Map<String, Object> paramMap);
	
}
