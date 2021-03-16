package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserOnline;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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