package com.yestae.user.manage.modular.vas.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.vas.persistence.model.OrganizVas;
import com.yestae.user.manage.modular.vas.persistence.model.Vas;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
public interface VasMapper extends BaseMapper<Vas> {

	List<OrganizVas> findOrganizVas(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> selectVasList(@Param("page") Page<Map<String, Object>> page, @Param("map") Map<String, Object> map);

}