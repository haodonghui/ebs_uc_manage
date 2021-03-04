package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserOnline;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-07
 */
public interface YestaeUserOnlineMapper extends BaseMapper<YestaeUserOnline> {

	List<Map<String, Object>> selectYestaeUserOnlineList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, String> map);

}