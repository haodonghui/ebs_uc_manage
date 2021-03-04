package com.yestae.user.manage.modular.privilege.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeUser;

/**
 * <p>
  * 推广人表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
public interface YestaeGeneralizeUserMapper extends BaseMapper<YestaeGeneralizeUser> {

	List<Map<String, Object>> selectYestaeGeneralizeUserList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, Object> map);

	int updateByMap(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> selectYestaeGeneralizeUserList(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> selectYestaeGeneralizeUserListDYCT(@Param("map") Map<String, Object> map);

}