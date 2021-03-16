package com.yestae.user.manage.modular.vas.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yestae.user.manage.modular.vas.persistence.model.Equity;
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
public interface EquityMapper extends BaseMapper<Equity> {

	List<Equity> findEquityListByMap(@Param("map") Map<String, Object> map);

}