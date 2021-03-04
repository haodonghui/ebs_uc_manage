package com.yestae.user.manage.modular.privilege.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.modular.privilege.common.Constants.UserConstant;
import com.yestae.user.manage.modular.privilege.common.enums.QrcodeSceneTypeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.common.enums.UserStatusEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeQrcodeMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeQrcodeSceneMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcodeScene;
import com.yestae.user.manage.modular.privilege.service.IYestaeQrcodeSceneService;

/**
 * <p>
 * 二维码场景表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-04
 */
@Service
public class YestaeQrcodeSceneServiceImpl extends ServiceImpl<YestaeQrcodeSceneMapper, YestaeQrcodeScene> implements IYestaeQrcodeSceneService {

	@Resource
	YestaeQrcodeSceneMapper yestaeQrcodeSceneMapper;
	
	@Resource
	YestaeQrcodeMapper yestaeQrcodeMapper;
	
	@Override
	@Transactional
	public int updateYestaeQrcodeScene(YestaeQrcodeScene yestaeQrcodeScene) {
		if(yestaeQrcodeScene == null){
			return -1;
		}
		
		YestaeQrcodeScene yestaeQrcodeSceneDb = yestaeQrcodeSceneMapper.selectById(yestaeQrcodeScene.getId());
		if(yestaeQrcodeSceneDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		BeanUtils.copyProperties(yestaeQrcodeScene, yestaeQrcodeSceneDb, new String[]{"create_time", "create_by", "if_del", "status"});
		
		return yestaeQrcodeSceneMapper.updateById(yestaeQrcodeSceneDb);
	}

	@Override
	public int deleteByYestaeQrcodeSceneId(String yestaeQrcodeSceneId) {
		
//		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
		
		YestaeQrcodeScene yestaeQrcodeSceneDb = yestaeQrcodeSceneMapper.selectById(yestaeQrcodeSceneId);
		if(yestaeQrcodeSceneDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		yestaeQrcodeSceneDb.setUpdateTime(new Date().getTime());
//		yestaeQrcodeSceneDb.setUpdateBy(shiroUser.getId());
		yestaeQrcodeSceneDb.setIfDel(SysEnum.YES.getCode());
		
		int i = yestaeQrcodeSceneMapper.updateById(yestaeQrcodeSceneDb);
		
		if(i > 0){
			
			Map<String, Object> map = new HashMap<>();
			map.put("ifDelYes", SysEnum.YES.getCode());
			map.put("ifDelNo", SysEnum.NO.getCode());
			map.put("updateTime", new Date().getTime());
//			map.put("updateBy", shiroUser.getId());
			map.put("sceneId", yestaeQrcodeSceneId);
			i = yestaeQrcodeMapper.updateByMap(map);
			if(i < 0){
				throw new BussinessException(BizExceptionEnum.DELETE_ERROE);
			}
		}
		
		return 1;
	}

	@Override
	public YestaeQrcodeScene selectDcardYestaeQrcodeScene() {
		
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("if_del", SysEnum.NO.getCode());
		columnMap.put("name", UserConstant.DCARD_SCENE_NAME);
		List<YestaeQrcodeScene> list = yestaeQrcodeSceneMapper.selectByMap(columnMap);
		if(list != null && list.size() > 0 && list.get(0) != null){
			return list.get(0);
		}else{
			YestaeQrcodeScene yestaeQrcodeScene = new YestaeQrcodeScene();
			yestaeQrcodeScene.setCreateTime(new Date().getTime());
	    	yestaeQrcodeScene.setIfDel(SysEnum.NO.getCode());
	    	yestaeQrcodeScene.setStatus(UserStatusEnum.STATUS_ON.getCode());
	    	yestaeQrcodeScene.setType(QrcodeSceneTypeEnum.WEIXIN_A_REGIST.getCode());
	    	yestaeQrcodeScene.setName(UserConstant.DCARD_SCENE_NAME);
	    	this.insert(yestaeQrcodeScene);
			return yestaeQrcodeScene;
		}
	}

	@Override
	public YestaeQrcodeScene selectTeaTicketYestaeQrcodeScene() {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("if_del", SysEnum.NO.getCode());
		columnMap.put("apply_scope", UserConstant.APPLY_SCOPE_COMMON);
		columnMap.put("type", UserConstant.TYPE_GET_TICKET);
		columnMap.put("jump_type", UserConstant.JUMP_TYPE_ORIGINAL);
		columnMap.put("name", UserConstant.TAE_TICKET_SCENE_NAME);
		List<YestaeQrcodeScene> list = yestaeQrcodeSceneMapper.selectByMap(columnMap);
		if(list != null && list.size() > 0 && list.get(0) != null){
			return list.get(0);
		}else{
			YestaeQrcodeScene yestaeQrcodeScene = new YestaeQrcodeScene();
			yestaeQrcodeScene.setCreateTime(new Date().getTime());
	    	yestaeQrcodeScene.setIfDel(SysEnum.NO.getCode());
	    	yestaeQrcodeScene.setStatus(UserStatusEnum.STATUS_ON.getCode());
	    	yestaeQrcodeScene.setType(UserConstant.TYPE_GET_TICKET);
	    	yestaeQrcodeScene.setName(UserConstant.TAE_TICKET_SCENE_NAME);
	    	yestaeQrcodeScene.setApplyScope(UserConstant.APPLY_SCOPE_COMMON);
	    	yestaeQrcodeScene.setJumpType(UserConstant.JUMP_TYPE_ORIGINAL);
	    	this.insert(yestaeQrcodeScene);
			return yestaeQrcodeScene;
		}
	}
}
