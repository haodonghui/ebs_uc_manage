package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserRelationMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserRelation;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户邀请关系 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@Service
public class YestaeUserRelationServiceImpl extends ServiceImpl<YestaeUserRelationMapper, YestaeUserRelation> implements IYestaeUserRelationService {

	@Resource
	YestaeUserRelationMapper yestaeUserRelationMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeUserRelationList(Page<Map<String, Object>> page,
																  Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		return yestaeUserRelationMapper.selectYestaeUserRelationList(page, map);
	}

}
