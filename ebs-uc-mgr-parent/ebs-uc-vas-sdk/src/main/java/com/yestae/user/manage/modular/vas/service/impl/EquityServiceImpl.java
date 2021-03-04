package com.yestae.user.manage.modular.vas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.dao.EquityMapper;
import com.yestae.user.manage.modular.vas.persistence.model.Equity;
import com.yestae.user.manage.modular.vas.service.IEquityService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
@Service
public class EquityServiceImpl extends ServiceImpl<EquityMapper, Equity> implements IEquityService {

	private final Logger logger = LoggerFactory.getLogger(EquityServiceImpl.class);
	@Resource
	EquityMapper equityMapper;
	@Resource
	IVasImageService vasImageService;
	
	@Override
	public List<Equity> findEquityListByVasId(String vasId) {
		Map<String, Object> map = new HashMap<>();
		map.put("vasId", vasId);
		return equityMapper.findEquityListByMap(map);
	}

	@Override
	public List<Equity> findEquityListByMap(Map<String, Object> map) {
		return equityMapper.findEquityListByMap(map);
	}


	@Override
	public void insertEquity(Equity equity) {

		if(equity != null){
			equity.setDelFlag(VasConstants.YES);
			equity.setStatus(VasConstants.STATUS_OFF);
			//编码校验
			Wrapper<Equity> wrapper1 = new EntityWrapper<>();
			wrapper1.eq("equity_code", equity.getEquityCode());
			wrapper1.eq("del_flag", VasConstants.YES);
			int count1 = this.selectCount(wrapper1);
			if(count1 > 0){
				throw new BussinessException(BizExceptionEnum.EQUITY_CODE_EXISTED);
			}
			
			//名称校验
			/*Wrapper<Equity> wrapper2 = new EntityWrapper<>();
			wrapper2.eq("equity_name", equity.getEquityName());
			int count2 = this.selectCount(wrapper2);
			if(count2 > 0){
				throw new BussinessException(BizExceptionEnum.EQUITY_NAME_EXISTED);
			}*/
			
			this.insert(equity);
			vasImageService.updateVasImage(equity.getSurfaceId(), equity.getId());
		}
	}
	
	@Override
	public void updateEquity(Equity equity) {
		// TODO Auto-generated method stub
		logger.info("EquityServiceImpl->updateEquity->equity:{}", JSONObject.toJSON(equity));
		if(equity != null){
			//编码校验
			Wrapper<Equity> wrapper = new EntityWrapper<>();
			wrapper.eq("equity_code", equity.getEquityCode());
			wrapper.eq("del_flag", VasConstants.YES);
			wrapper.ne("id", equity.getId());
			int count = this.selectCount(wrapper);
			if(count > 0){
				throw new BussinessException(BizExceptionEnum.EQUITY_CODE_EXISTED);
			}
			//名称校验
			Wrapper<Equity> wrapper3 = new EntityWrapper<>();
			wrapper3.eq("equity_name", equity.getEquityName());
			wrapper3.eq("del_flag", VasConstants.YES);
			wrapper3.ne("id", equity.getId());
			int count3 = this.selectCount(wrapper3);
			if(count3 > 0){
				throw new BussinessException(BizExceptionEnum.EQUITY_NAME_EXISTED);
			}
			
			
			Equity equityDb = this.selectById(equity.getId());
			if(equityDb == null){
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			BeanUtils.copyProperties(equity, equityDb, new String[]{"createTime", "createBy", "delFlag", "status"});
			this.updateById(equityDb);
			vasImageService.updateVasImage(equity.getSurfaceId(), equity.getId());
		}else{
			logger.error("EquityServiceImpl->updateEquity->equity:{}", JSONObject.toJSON(equity));
		}
	}
	
}
