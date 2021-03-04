package com.yestae.user.manage.modular.privilege.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccountFlow;

/**
 * <p>
  * 账户资金变动流水 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
public interface UserAccountFlowMapper extends BaseMapper<UserAccountFlow> {

	List<Map<String, Object>> selectUserAccountFlowList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, String> map);

	List<Map<String, Object>> selectUserAccountFlowList(@Param("map") Map<String, String> map);

}