package com.yestae.user.manage.modular.vas.service;

import com.yestae.user.manage.modular.vas.persistence.model.OrganizVas;
import com.yestae.user.manage.modular.vas.persistence.model.Vas;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
public interface IVasService extends IService<Vas> {
	
	public List<OrganizVas> findAllOrganizVas(Map<String, Object> map);

	public List<Map<String, Object>> selectVasList(Page<Map<String, Object>> page, Map<String, Object> map);

	/**
	 * 添加增值服务
	 * @param vas
	 */
	public void insertVas(Vas vas);

	/**
	 * 修改增值服务
	 * @param vas
	 */
	public void updateVas(Vas vas);
}
