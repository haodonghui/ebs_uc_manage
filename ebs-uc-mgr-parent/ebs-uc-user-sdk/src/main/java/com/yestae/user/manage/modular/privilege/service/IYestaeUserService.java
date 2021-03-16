package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUser;
import com.yestae.user.manage.modular.privilege.vo.YestaeUserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-03
 */
public interface IYestaeUserService extends IService<YestaeUser> {

	/**
	 * 用户修改
	 * @param yestaeUserVo
	 * @return
	 */
	int updateYestaeUser(YestaeUserVo yestaeUserVo);

	/**
	 * 修改手机号
	 * @param yestaeUserVo
	 * @return
	 */
	int updateMobile(YestaeUserVo yestaeUserVo);
	
	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	YestaeUser selectByUserId(String userId);
	
}
