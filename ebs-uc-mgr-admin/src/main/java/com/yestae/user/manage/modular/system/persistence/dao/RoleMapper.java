package com.yestae.user.manage.modular.system.persistence.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yestae.user.manage.modular.system.persistence.model.Role;

/**
 * <p>
  * 角色表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface RoleMapper extends BaseMapper<Role> {

	Integer findRoleNumCount(@Param("id") String id, @Param("pid") String pid, @Param("num") Integer num);

}