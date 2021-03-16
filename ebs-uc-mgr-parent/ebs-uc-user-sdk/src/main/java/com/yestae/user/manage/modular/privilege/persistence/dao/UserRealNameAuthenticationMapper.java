package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.UserRealNameAuthentication;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 实名认证 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2018-03-23
 */
public interface UserRealNameAuthenticationMapper extends BaseMapper<UserRealNameAuthentication> {

	int selectCountByIdNo(@Param("idNo") String idNo, @Param("idType") String idType);

	List<Map<String, Object>> selectUserRealNameAuthenticationList(@Param("page") Page<Map<String, Object>> page,
			@Param("map") Map<String, String> map);

}