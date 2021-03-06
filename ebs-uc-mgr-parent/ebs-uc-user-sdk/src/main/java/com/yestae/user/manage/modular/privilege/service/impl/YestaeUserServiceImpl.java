package com.yestae.user.manage.modular.privilege.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserAddressMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeUserModifyPhoneLogMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUser;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyPhoneLog;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserService;
import com.yestae.user.manage.modular.privilege.vo.YestaeUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-03
 */
@Service
public class YestaeUserServiceImpl extends ServiceImpl<YestaeUserMapper, YestaeUser> implements IYestaeUserService {

	@Resource
	YestaeUserAddressMapper yestaeUserAddressMapper;
	@Resource
	YestaeUserModifyPhoneLogMapper yestaeUserModifyPhoneLogMapper;
	
	@Override
	@Transactional
	public int updateYestaeUser(YestaeUserVo yestaeUserVo) {
		if(yestaeUserVo == null){
			return -1;
		}
		String id = yestaeUserVo.getId();
		YestaeUser yestaeUser = super.getById(id);
		if(yestaeUser == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		yestaeUser.setCardNo(yestaeUserVo.getCardNo());
		yestaeUser.setUpdateTime(new Date().getTime());
		super.updateById(yestaeUser);
		return 1;
	}

	@Override
	@Transactional
	public int updateMobile(YestaeUserVo yestaeUserVo) {
		
		if(yestaeUserVo == null){
			return -1;
		}
//		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
		String id = yestaeUserVo.getId();
		YestaeUser yestaeUser = super.getById(id);
		if(yestaeUser == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		
		String sourceMobile = yestaeUser.getMobile();
		String targetMobile = yestaeUserVo.getMobile();
		if(targetMobile!= null && !targetMobile.equals(sourceMobile)){
			
			//查询手机号是否存在
			QueryWrapper<YestaeUser> wrapper = new QueryWrapper<>();
			wrapper.eq("if_del", SysEnum.NO.getCode());
			wrapper.eq("mobile", targetMobile);
			int total = this.count(wrapper );
			if(total > 0){
				throw new BussinessException(BizExceptionEnum.MOBILE_EXISTED);
			}
			
			//修改用户手机号并记录日志
			YestaeUserModifyPhoneLog yestaeUserModifyPhoneLog = new YestaeUserModifyPhoneLog();
			yestaeUserModifyPhoneLog.setCreateTime(new Date().getTime());
//			yestaeUserModifyPhoneLog.setOperatorId(shiroUser.getId());
//			yestaeUserModifyPhoneLog.setOperatorName(shiroUser.getName());
			yestaeUserModifyPhoneLog.setOperatorId(yestaeUserVo.getOperatorId());
			yestaeUserModifyPhoneLog.setOperatorName(yestaeUserVo.getOperatorName());
			yestaeUserModifyPhoneLog.setSourceMobile(sourceMobile);
			yestaeUserModifyPhoneLog.setTargetMobile(targetMobile);
			yestaeUserModifyPhoneLog.setUserId(yestaeUser.getUserId());
			yestaeUserModifyPhoneLog.setRemark(yestaeUserVo.getRemark());
			yestaeUserModifyPhoneLogMapper.insert(yestaeUserModifyPhoneLog);
			
			yestaeUser.setMobile(targetMobile);
		}
		
		super.updateById(yestaeUser);
		return 1;
	}

	@Override
	public YestaeUser selectByUserId(String userId) {
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		QueryWrapper<YestaeUser> wrapper = new QueryWrapper<>();
		wrapper.eq("if_del", SysEnum.NO.getCode());
		wrapper.eq("user_id", userId);
		return this.getOne(wrapper);
	}
	
}
