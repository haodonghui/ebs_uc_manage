package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserGrade;

/**
 * <p>
 * 用户会员等级 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface IYestaeUserGradeService extends IService<YestaeUserGrade> {

	int updateYestaeUserGrade(YestaeUserGrade yestaeUserGrade);
	
}
