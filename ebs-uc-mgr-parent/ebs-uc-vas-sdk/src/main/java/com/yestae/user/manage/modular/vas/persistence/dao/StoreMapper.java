package com.yestae.user.manage.modular.vas.persistence.dao;

import com.yestae.user.manage.modular.vas.persistence.model.Store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 门店 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
public interface StoreMapper extends BaseMapper<Store> {

	List<Map<String, Object>> selectStoreList(@Param("page") Page<Map<String, Object>> page, @Param("map") Map<String, Object> map);

	Object insertStoreList(Store store);

	

}