package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.UserAccountFlowMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccountFlow;
import com.yestae.user.manage.modular.privilege.service.IUserAccountFlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账户资金变动流水 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
@Service
public class UserAccountFlowServiceImpl extends ServiceImpl<UserAccountFlowMapper, UserAccountFlow> implements IUserAccountFlowService {

	@Resource
	UserAccountFlowMapper userAccountFlowMapper;
	
	@Override
	public List<Map<String, Object>> selectUserAccountFlowList(Page<Map<String, Object>> page,
															   Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		if(page != null){
			return userAccountFlowMapper.selectUserAccountFlowList(page, map);
		}else{
			return userAccountFlowMapper.selectUserAccountFlowList(map);
		}
	}
	
}
