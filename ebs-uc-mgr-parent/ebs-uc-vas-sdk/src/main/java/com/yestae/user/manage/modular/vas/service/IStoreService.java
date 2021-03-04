package com.yestae.user.manage.modular.vas.service;

import com.yestae.user.manage.modular.vas.persistence.model.Store;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 门店 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
public interface IStoreService extends IService<Store> {

	
	List<Map<String, Object>> selectList(Page<Map<String, Object>> page, Map<String, Object> paramMap);

	void insertStore(Store store);

	void updateStore(Store store);

	
	
	
	
}
