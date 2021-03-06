package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户邀请关系 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface YestaeUserRelationMapper extends BaseMapper<YestaeUserRelation> {

	/**
	 * 查询用户关联关系列表
	 * @param page 分页
	 * @param map 查询参数
	 * @return
	 */
	List<Map<String, Object>> selectYestaeUserRelationList(@Param("page") Page<Map<String, Object>> page, @Param("map") Map<String, String> map);

}