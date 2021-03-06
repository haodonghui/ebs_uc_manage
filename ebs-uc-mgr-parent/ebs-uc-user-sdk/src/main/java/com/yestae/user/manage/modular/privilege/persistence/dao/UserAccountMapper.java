package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户账户信息 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

	
	List<Map<String, Object>> selectUserAccountList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, String> map);

	List<Map<String, Object>> selectUserAccountList(@Param("map") Map<String, String> map);

}