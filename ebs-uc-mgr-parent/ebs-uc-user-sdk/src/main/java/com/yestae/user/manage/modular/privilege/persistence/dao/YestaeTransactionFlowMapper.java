package com.yestae.user.manage.modular.privilege.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeTransactionFlow;

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