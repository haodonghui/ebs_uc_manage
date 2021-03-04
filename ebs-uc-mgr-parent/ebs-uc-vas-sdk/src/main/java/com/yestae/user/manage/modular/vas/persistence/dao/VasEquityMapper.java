package com.yestae.user.manage.modular.vas.persistence.dao;

import com.yestae.user.manage.modular.vas.persistence.model.VasEquity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 增值服务权益关联表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
public interface VasEquityMapper extends BaseMapper<VasEquity> {

	List<Map<String, Object>> selectVasEquityList(@Param("map") Map<String, Object> map);

}