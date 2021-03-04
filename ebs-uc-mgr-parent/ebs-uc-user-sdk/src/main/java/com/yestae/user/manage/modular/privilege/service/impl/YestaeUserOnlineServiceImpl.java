package com.yestae.user.manage.modular.privilege.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserOnlineMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserOnline;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserOnlineService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-07
 */
@Service
public class YestaeUserOnlineServiceImpl extends ServiceImpl<YestaeUserOnlineMapper, YestaeUserOnline> implements IYestaeUserOnlineService {

	@Resource
	YestaeUserOnlineMapper yestaeUserOnlineMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeUserOnlineList(Page<Map<String, Object>> page,
			Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		return yestaeUserOnlineMapper.selectYestaeUserOnlineList(page, map);
	}
	
}
