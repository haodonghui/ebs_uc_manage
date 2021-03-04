package com.yestae.user.manage.modular.vas.service.impl;

import com.yestae.user.manage.modular.vas.persistence.model.Equity;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;
import com.yestae.user.manage.modular.vas.persistence.model.Store;
import com.yestae.user.manage.modular.vas.persistence.model.Vas;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.dao.StoreMapper;
import com.yestae.user.manage.modular.vas.persistence.dao.VasMapper;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IStoreService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 门店 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {

	private final Logger logger = LoggerFactory.getLogger(EquityServiceImpl.class);
	@Resource
	StoreMapper storeMapper;
	@Autowired
	private IOrganizService organizService;
	@Resource
	IVasImageService vasImageService;
	
	@Override
	public List<Map<String, Object>> selectList(Page<Map<String, Object>> page, Map<String, Object> map) {
		// TODO Auto-generated method stub
		map.put("delFlag", VasConstants.YES);
		return storeMapper.selectStoreList(page, map);
	}
	//添加
	@Override
	public void insertStore(Store store) {
		// TODO Auto-generated method stub
		if(store != null){
			store.setDelFlag(VasConstants.YES);
			store.setStatus(VasConstants.STATUS_OFF);
			//校验门店名称
			Wrapper<Store> wrapper1 = new EntityWrapper<>();
			wrapper1.eq("store_name", store.getStoreName());
			wrapper1.eq("organiz_id", store.getOrganizId());
			int count1 = this.selectCount(wrapper1);
			if(count1 > 0){
				throw new BussinessException(BizExceptionEnum.ORGANIZ_STORE_NAME_EXISTED);
			}
			
			//添加机构编码
			if(!StringUtils.isEmpty(store.getOrganizId())){
				Organiz organiz = organizService.selectById(store.getOrganizId());
				if(organiz != null){
					store.setOrganizCode(organiz.getOrganizCode());
				}
			}
			//添加门店
			this.insert(store);
			//添加增值服务图片
			vasImageService.updateVasImage(store.getSurfaceId(), store.getId());
		}
	}
	//修改
	@Override
	public void updateStore(Store store) {
		// TODO Auto-generated method stub
		logger.info("StoreServiceImpl->updateStore->store:{}", JSONObject.toJSON(store));
		if(store != null){
			//校验门店名称
			Wrapper<Store> wrapper2 = new EntityWrapper<>();
			wrapper2.eq("store_name", store.getStoreName());
			wrapper2.eq("organiz_id", store.getOrganizId());
			wrapper2.ne("id", store.getId());
			int count2 = this.selectCount(wrapper2);
			if(count2 > 0){
				throw new BussinessException(BizExceptionEnum.ORGANIZ_STORE_NAME_EXISTED);
			}
			
			Store storeDb = this.selectById(store.getId());
			if(storeDb == null){
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			//添加机构编码
			if(store.getOrganizId() != null && !store.getOrganizId().equals(storeDb.getOrganizId())){
				Organiz organiz = organizService.selectById(store.getOrganizId());
				if(organiz != null){
					store.setOrganizCode(organiz.getOrganizCode());
				}
			}
			
			if(storeDb == null){
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			BeanUtils.copyProperties(store, storeDb, new String[]{"createTime", "createBy", "delFlag", "status"});
			this.updateById(storeDb);
			vasImageService.updateVasImage(store.getSurfaceId(), store.getId());
		}else{
			logger.error("StoreServiceImpl->updateEquity->store:{}", JSONObject.toJSON(store));
		}
	}

	
	
}
