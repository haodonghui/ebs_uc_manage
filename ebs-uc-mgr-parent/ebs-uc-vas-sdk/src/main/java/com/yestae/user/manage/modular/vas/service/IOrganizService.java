package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
public interface IOrganizService extends IService<Organiz> {

	/**
	 * 新增机构
	 * @param organiz
	 */
	void insertOrganiz(Organiz organiz);

	/**
	 * 修改机构
	 * @param organiz
	 */
	void updateOrganiz(Organiz organiz);
	
}
