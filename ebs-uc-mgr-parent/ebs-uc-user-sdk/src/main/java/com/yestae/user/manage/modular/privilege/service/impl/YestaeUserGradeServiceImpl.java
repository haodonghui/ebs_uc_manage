package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserGradeMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserGrade;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserGradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户会员等级 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@Service
public class YestaeUserGradeServiceImpl extends ServiceImpl<YestaeUserGradeMapper, YestaeUserGrade> implements IYestaeUserGradeService {

	@Override
	public int updateYestaeUserGrade(YestaeUserGrade yestaeUserGrade) {
		
		YestaeUserGrade yestaeUserGradeDb = this.getById(yestaeUserGrade.getId());
		//如果设置为默认则自动设置其他用户等级为非默认
		if(SysEnum.YES.getCode() == yestaeUserGrade.getIfDefault() 
				&& SysEnum.YES.getCode() != yestaeUserGradeDb.getIfDefault()){
			QueryWrapper<YestaeUserGrade> wrapper = new QueryWrapper<>();
	    	wrapper.eq("if_del", SysEnum.NO.getCode());
	    	wrapper.eq("if_default", SysEnum.YES.getCode());
			List<YestaeUserGrade> list = this.list(wrapper);
			if(list != null && list.size() > 0){
				for(YestaeUserGrade g: list){
					g.setIfDefault(SysEnum.NO.getCode());
				}
				this.updateBatchById(list);
			}
		}
		
		BeanUtils.copyProperties(yestaeUserGrade, yestaeUserGradeDb, new String[] {"createBy", "createTime", "ifDel"});
		this.updateById(yestaeUserGradeDb);
		
		return 1;
	}
	
}
