package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserRelation;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户邀请关系 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface IYestaeUserRelationService extends IService<YestaeUserRelation> {

	/**
	 * 查询用户邀请关系列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeUserRelationList(Page<Map<String, Object>> page, Map<String, String> map);

}
