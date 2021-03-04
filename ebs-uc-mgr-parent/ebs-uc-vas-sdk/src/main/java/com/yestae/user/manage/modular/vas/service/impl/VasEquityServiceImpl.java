package com.yestae.user.manage.modular.vas.service.impl;

import com.yestae.user.manage.modular.vas.persistence.model.VasEquity;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.dao.VasEquityMapper;
import com.yestae.user.manage.modular.vas.service.IVasEquityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 增值服务权益关联表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
@Service
public class VasEquityServiceImpl extends ServiceImpl<VasEquityMapper, VasEquity> implements IVasEquityService {

	@Resource
	VasEquityMapper vasEquityMapper;
	
	@Override
	public List<Map<String, Object>> selectVasEquityList(Map<String, Object> paramMap) {
		paramMap.put("delFlag", VasConstants.YES);
		return vasEquityMapper.selectVasEquityList(paramMap);
	}
	
}
