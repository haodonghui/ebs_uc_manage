package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推广人表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
public interface IYestaeGeneralizeUserService extends IService<YestaeGeneralizeUser> {

	/**
	 * 查询推广人列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeGeneralizeUserList(Page<Map<String, Object>> page, Map<String, Object> map);

	/**
	 * 新增推广人
	 * @param yestaeGeneralizeUser
	 * @return
	 */
	int insertYestaeGeneralizeUser(YestaeGeneralizeUser yestaeGeneralizeUser);
	
	/**
	 * 系统自动生成推广人
	 * @param yestaeGeneralizeUser
	 * @return
	 */
	int insertYestaeGeneralizeUserAuto(YestaeGeneralizeUser yestaeGeneralizeUser);

	/**
	 * 修改推广人
	 * @param yestaeGeneralizeUser
	 * @return
	 */
	int updateYestaeGeneralizeUser(YestaeGeneralizeUser yestaeGeneralizeUser);

	/**
	 * 删除推广人
	 * @param yestaeGeneralizeUserId
	 * @return
	 */
	int deleteByYestaeGeneralizeUserId(String yestaeGeneralizeUserId);

	/**
	 * 获取大益茶庭子渠道下所有推广人
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeGeneralizeUserListDYCT(Map<String, Object> map);
	
}
