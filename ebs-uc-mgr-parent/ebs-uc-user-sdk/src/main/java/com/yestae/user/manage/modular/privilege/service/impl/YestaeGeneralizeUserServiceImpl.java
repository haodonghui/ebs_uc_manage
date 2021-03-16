package com.yestae.user.manage.modular.privilege.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.common.util.ToolUtil;
import com.yestae.user.manage.modular.privilege.common.Constants.UserConstant;
import com.yestae.user.manage.modular.privilege.common.enums.InviterIsFCodeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeGeneralizeUserMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeQrcodeMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeUser;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeChannelService;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeUserService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推广人表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
@Service
public class YestaeGeneralizeUserServiceImpl extends ServiceImpl<YestaeGeneralizeUserMapper, YestaeGeneralizeUser> implements IYestaeGeneralizeUserService {

	private final Logger logger = LoggerFactory.getLogger(YestaeGeneralizeUserServiceImpl.class);
	
	@Resource
	YestaeGeneralizeUserMapper yestaeGeneralizeUserMapper;
	
	@Resource
	IYestaeGeneralizeChannelService yestaeGeneralizeChannelService;
	
	@Resource
	YestaeQrcodeMapper yestaeQrcodeMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeGeneralizeUserList(Page<Map<String, Object>> page,
																	Map<String, Object> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		if(!StringUtils.isEmpty(MapUtils.getString(map, "generalizeUserIds"))){
			map.put("generalizeUserIds", MapUtils.getString(map, "generalizeUserIds").split(","));
		}
		if(page == null){
			return yestaeGeneralizeUserMapper.selectYestaeGeneralizeUserList(map);
		}
		return yestaeGeneralizeUserMapper.selectYestaeGeneralizeUserList(page, map);
	}

	@Override
	@Transactional
	public int insertYestaeGeneralizeUser(YestaeGeneralizeUser yestaeGeneralizeUser) {
		if(yestaeGeneralizeUser == null){
			return -1;
		}
		
		if(!this.checkUserId(yestaeGeneralizeUser)){
			throw new BussinessException(BizExceptionEnum.YESTAE_USER_HAS_BEEN_USED);
		}
		
		return yestaeGeneralizeUserMapper.insert(yestaeGeneralizeUser);
	}
	
	@Override
	public int insertYestaeGeneralizeUserAuto(YestaeGeneralizeUser yestaeGeneralizeUser) {
		yestaeGeneralizeUser.setCreateTime(new Date().getTime());
    	yestaeGeneralizeUser.setIfDel(SysEnum.NO.getCode());
    	yestaeGeneralizeUser.setInviterIsFCode(InviterIsFCodeEnum.F_CODE_NONE.getCode());
    	yestaeGeneralizeUser.setRecommendCode(ToolUtil.getRandomString(6));
		yestaeGeneralizeUser.setRemark("系统自动生成");
		
		return yestaeGeneralizeUserMapper.insert(yestaeGeneralizeUser);
	}

	@Override
	@Transactional
	public int updateYestaeGeneralizeUser(YestaeGeneralizeUser yestaeGeneralizeUser) {
		if(yestaeGeneralizeUser == null){
			return -1;
		}
		
		YestaeGeneralizeUser yestaeGeneralizeUserDb = yestaeGeneralizeUserMapper.selectById(yestaeGeneralizeUser.getId());
		if(yestaeGeneralizeUserDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		if(!this.checkUserId(yestaeGeneralizeUser)){
			throw new BussinessException(BizExceptionEnum.YESTAE_USER_HAS_BEEN_USED);
		}
		
		BeanUtils.copyProperties(yestaeGeneralizeUser, yestaeGeneralizeUserDb, new String[] {"createBy", "createTime", "ifDel", "inviterIsFCode", "recommendCode"});
		
		int result = yestaeGeneralizeUserMapper.updateById(yestaeGeneralizeUserDb);
		
		logger.info("updateYestaeGeneralizeUser->result:" + result + " yestaeGeneralizeUser[source]:" + JSONObject.toJSONString(yestaeGeneralizeUser));
		logger.info("updateYestaeGeneralizeUser->result:" + result + " yestaeGeneralizeUser[target]:" + JSONObject.toJSONString(yestaeGeneralizeUserDb));
		
		return result;
	}
	
	/**
	 * 查询用户是否关联
	 * @param yestaeGeneralizeUser
	 * @return
	 */
	private boolean checkUserId(YestaeGeneralizeUser yestaeGeneralizeUser){
		
		if(yestaeGeneralizeUser.getUserId() == null){
			return true;
		}
		
		QueryWrapper<YestaeGeneralizeUser> wrapper = new QueryWrapper<YestaeGeneralizeUser>();
		wrapper.eq("if_del", SysEnum.NO.getCode());
		wrapper.eq("user_id", yestaeGeneralizeUser.getUserId());
		if(!StringUtils.isEmpty(yestaeGeneralizeUser.getId())){
			wrapper.ne("id", yestaeGeneralizeUser.getId());
		}
		int i = yestaeGeneralizeUserMapper.selectCount(wrapper);
		if(i > 0){
			return false;
		}
		
		return true;
	}

	@Override
	public int deleteByYestaeGeneralizeUserId(String yestaeGeneralizeUserId) {
		
//		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
				
		YestaeGeneralizeUser yestaeGeneralizeUserDb = yestaeGeneralizeUserMapper.selectById(yestaeGeneralizeUserId);
		if(yestaeGeneralizeUserDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		yestaeGeneralizeUserDb.setUpdateTime(new Date().getTime());
//		yestaeGeneralizeUserDb.setUpdateBy(shiroUser.getId());
		yestaeGeneralizeUserDb.setIfDel(SysEnum.YES.getCode());
		
		int i = yestaeGeneralizeUserMapper.updateById(yestaeGeneralizeUserDb);
		
		if(i > 0){
			
			Map<String, Object> map = new HashMap<>();
			map.put("ifDelYes", SysEnum.YES.getCode());
			map.put("ifDelNo", SysEnum.NO.getCode());
			map.put("updateTime", new Date().getTime());
//			map.put("updateBy", shiroUser.getId());
			map.put("generalizeUserId", yestaeGeneralizeUserId);
			i = yestaeQrcodeMapper.updateByMap(map);
			if(i < 0){
				throw new BussinessException(BizExceptionEnum.DELETE_ERROE);
			}
		}
		
		return 1;
	}

	@Override
	public List<Map<String, Object>> selectYestaeGeneralizeUserListDYCT(Map<String, Object> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		if(StringUtils.isEmpty(MapUtils.getString(map, "channelCode"))){
			map.put("channelCode", UserConstant.CHANNEL_CODE_DYCT);
		}
		map.put("ifEndYes", SysEnum.YES.getCode() + "");
		return yestaeGeneralizeUserMapper.selectYestaeGeneralizeUserListDYCT(map);
	}
}
