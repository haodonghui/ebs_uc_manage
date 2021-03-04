package com.yestae.user.manage.modular.privilege.service;

import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralize;

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
 * @since 2017-12-06
 */
public interface IYestaeGeneralizeService extends IService<YestaeGeneralize> {

	/**
	 * 查询推广明细列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeGeneralizeList(Page<Map<String, Object>> page, Map<String, String> map);

	/**
	 * 查询用户邀请关系列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeUserRelationList(Page<Map<String, Object>> page, Map<String, String> map);

	/**
	 * 门店注册会员商城销售统计列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectSaleList(Page<Map<String, Object>> page, Map<String, String> map);
	/**
	 * 门店注册会员商城销售统计详情列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectSaleDetailList(Page<Map<String, Object>> page, Map<String, String> map);
	
}
