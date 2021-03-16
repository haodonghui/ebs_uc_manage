package com.yestae.user.manage.modular.vas.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.vas.persistence.model.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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