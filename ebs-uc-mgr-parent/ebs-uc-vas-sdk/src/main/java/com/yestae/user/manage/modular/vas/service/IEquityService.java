package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.Equity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
public interface IEquityService extends IService<Equity> {
	
	/**
	 * 根据增值服务id查询所有关联权益
	 * @param vasId
	 *     增值服务id
	 * @return
	 */
	public List<Equity> findEquityListByVasId(String vasId);

	/**
	 * 根据条件查询所有关联权益
	 * @param map
	 *     条件
	 * @return
	 */
	public List<Equity> findEquityListByMap(Map<String, Object> map);

	

	public void insertEquity(Equity equity);

	public void updateEquity(Equity equity);
}
