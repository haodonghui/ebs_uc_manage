package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeTransactionFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-16
 */
public interface YestaeTransactionFlowMapper extends BaseMapper<YestaeTransactionFlow> {

	List<Map<String, Object>> selectYestaeTransactionFlowList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, String> map);

	List<Map<String, Object>> selectYestaeTransactionFlowList(@Param("map") Map<String, String> map);

}