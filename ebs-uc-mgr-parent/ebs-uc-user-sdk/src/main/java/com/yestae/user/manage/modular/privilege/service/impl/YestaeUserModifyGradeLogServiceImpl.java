package com.yestae.user.manage.modular.privilege.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserModifyGradeLogMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyGradeLog;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserModifyGradeLogService;

/**
 * <p>
 * 用户会员等级变更日志 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@Service
public class YestaeUserModifyGradeLogServiceImpl extends ServiceImpl<YestaeUserModifyGradeLogMapper, YestaeUserModifyGradeLog> implements IYestaeUserModifyGradeLogService {

	@Resource
	YestaeUserModifyGradeLogMapper yestaeUserModifyGradeLogMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeUserModifyGradeLogList(Page<Map<String, Object>> page,
			Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		return yestaeUserModifyGradeLogMapper.selectYestaeUserModifyGradeLogList(page, map);
	}
	
}
