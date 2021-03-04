package com.yestae.user.manage.modular.privilege.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeTransactionFlowMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeTransactionFlow;
import com.yestae.user.manage.modular.privilege.service.IYestaeTransactionFlowService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-16
 */
@Service
public class YestaeTransactionFlowServiceImpl extends ServiceImpl<YestaeTransactionFlowMapper, YestaeTransactionFlow> implements IYestaeTransactionFlowService {

	@Resource
	YestaeTransactionFlowMapper yestaeTransactionFlowMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeTransactionFlowList(Page<Map<String, Object>> page,
			Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		if(page != null){
			return yestaeTransactionFlowMapper.selectYestaeTransactionFlowList(page, map);
		}else{
			return yestaeTransactionFlowMapper.selectYestaeTransactionFlowList(map);
		}
	}
	
}
