package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralize;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-06
 */
public interface YestaeGeneralizeMapper extends BaseMapper<YestaeGeneralize> {

	List<Map<String, Object>> selectYestaeGeneralizeList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, String> map);
	
	List<Map<String, Object>> selectYestaeGeneralizeList(@Param("rowBounds") RowBounds rowBounds, @Param("map") Map<String, String> map);
	
	List<Map<String, Object>> selectYestaeGeneralizeList(@Param("map") Map<String, String> map);

	int selectYestaeGeneralizeListCount(@Param("map") Map<String, String> map);

	List<Map<String, Object>> selectYestaeUserRelationList(@Param("page") Page<Map<String, Object>> page, @Param("map") Map<String, String> map);

//	int selectSaleListCount(@Param("map") Map<String, String> map);

	List<Map<String, Object>> selectSaleList(@Param("rowBounds") RowBounds rowBounds, @Param("map") Map<String, String> map);

	List<Map<String, Object>> selectSaleList(@Param("map") Map<String, String> map);
	
//	int selectSaleDetailListCount(@Param("map") Map<String, String> map);
	
	List<Map<String, Object>> selectSaleDetailList(@Param("page") Page<Map<String, Object>> page, @Param("map") Map<String, String> map);
	
	List<Map<String, Object>> selectSaleDetailList(@Param("map") Map<String, String> map);

	int selectSaleListCount(@Param("map") Map<String, String> map);

}