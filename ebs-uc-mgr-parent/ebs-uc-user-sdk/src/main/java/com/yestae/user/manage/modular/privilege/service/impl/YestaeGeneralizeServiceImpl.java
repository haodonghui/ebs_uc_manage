package com.yestae.user.manage.modular.privilege.service.impl;

import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralize;
import com.yestae.user.manage.modular.privilege.common.Constants.UserConstant;
import com.yestae.user.manage.modular.privilege.common.enums.FlowTypeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.GeneralizeType;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeGeneralizeMapper;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-06
 */
@Service
public class YestaeGeneralizeServiceImpl extends ServiceImpl<YestaeGeneralizeMapper, YestaeGeneralize> implements IYestaeGeneralizeService {

	@Resource
	YestaeGeneralizeMapper yestaeGeneralizeMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeGeneralizeList(Page<Map<String, Object>> page,
			Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		map.put("ifEndNo", SysEnum.NO.getCode() + "");
		map.put("ifEndYes", SysEnum.YES.getCode() + "");
		map.put("registType", GeneralizeType.REGIST.getCode() + "");
		map.put("upgradeType", GeneralizeType.UPGRADE.getCode() + "");
		if(page != null){
			int total = yestaeGeneralizeMapper.selectYestaeGeneralizeListCount(map);
			page.setTotal(total);
			return yestaeGeneralizeMapper.selectYestaeGeneralizeList(new RowBounds(page.getOffset(), page.getLimit()), map);
		}
		return yestaeGeneralizeMapper.selectYestaeGeneralizeList(map);
	}

	@Override
	public List<Map<String, Object>> selectYestaeUserRelationList(Page<Map<String, Object>> page,
			Map<String, String> map) {
		
		map.put("ifDel", SysEnum.NO.getCode() + "");
		map.put("userType", UserConstant.USER_TYPE_COMMON + "");
		map.put("registType", GeneralizeType.REGIST.getCode() + "");
		map.put("upgradeType", GeneralizeType.UPGRADE.getCode() + "");
		return yestaeGeneralizeMapper.selectYestaeUserRelationList(page, map);
	}

	@Override
	public List<Map<String, Object>> selectSaleList(Page<Map<String, Object>> page, Map<String, String> map) {
		map.put("flowType", FlowTypeEnum.CONSUME.getCode() + "");
		map.put("ifDel", SysEnum.NO.getCode() + "");
		map.put("ifEndNo", SysEnum.NO.getCode() + "");
		map.put("ifEndYes", SysEnum.YES.getCode() + "");
		map.put("registType", GeneralizeType.REGIST.getCode() + "");
		if(StringUtils.isEmpty(MapUtils.getString(map, "channelCode"))){
			map.put("channelCode", UserConstant.CHANNEL_CODE_DYCT);
		}
		if(page != null){
			int total = yestaeGeneralizeMapper.selectSaleListCount(map);
			page.setTotal(total);
			return yestaeGeneralizeMapper.selectSaleList(new RowBounds(page.getOffset(), page.getLimit()), map);
		}
		return yestaeGeneralizeMapper.selectSaleList(map);
	}
	
	@Override
	public List<Map<String, Object>> selectSaleDetailList(Page<Map<String, Object>> page, Map<String, String> map) {
		map.put("flowType", FlowTypeEnum.CONSUME.getCode() + "");
		map.put("ifDel", SysEnum.NO.getCode() + "");
		map.put("ifEndNo", SysEnum.NO.getCode() + "");
		map.put("ifEndYes", SysEnum.YES.getCode() + "");
		map.put("registType", GeneralizeType.REGIST.getCode() + "");
		if(StringUtils.isEmpty(MapUtils.getString(map, "channelCode"))){
			map.put("channelCode", UserConstant.CHANNEL_CODE_DYCT);
		}
		if(page != null){
			return yestaeGeneralizeMapper.selectSaleDetailList(page, map);
		}
		return yestaeGeneralizeMapper.selectSaleDetailList(map);
	}
	
}
