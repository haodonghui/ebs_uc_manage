package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.Store;

import java.util.List;
import java.util.Map;

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
