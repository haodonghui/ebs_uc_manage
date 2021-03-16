package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserModifyPhoneLogMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyPhoneLog;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserModifyPhoneLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户手机号变更日志 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@Service
public class YestaeUserModifyPhoneLogServiceImpl extends ServiceImpl<YestaeUserModifyPhoneLogMapper, YestaeUserModifyPhoneLog> implements IYestaeUserModifyPhoneLogService {

	@Resource
	YestaeUserModifyPhoneLogMapper yestaeUserModifyPhoneLogMapper;
	
//	@Resource
//	UserMapper userMapper;
	
	
	@Override
//	@DataSource(name="dataSourceUc")
	public List<Map<String, Object>> selectYestaeUserModifyPhoneLogList(Page<Map<String, Object>> page,
																		Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		List<Map<String, Object>> list = yestaeUserModifyPhoneLogMapper.selectYestaeUserModifyPhoneLogList(page, map);
		//this.dealList(list);
		return list;
	}
	
	/**
	 * 处理手机号变更日志列表数据
	 * @param list
	 */
	@Override
//	@DataSource(name="dataSourceUcMgr")
	public void dealList(List<Map<String, Object>> list){
		/*for(Map<String, Object> m: list){
			
			m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
			String id = MapUtils.getString(m, "operatorId");
			if(!StringUtils.isEmpty(id)){
				if(id.equals(MapUtils.getString(m, "userId"))){
					m.put("operatorName", MapUtils.getString(m, "name"));
				}else{
					User user = userMapper.selectById(id);
					if(user != null){
						m.put("operatorName", user.getName());
					}
				}
			}
    	}*/
	}
	
}
