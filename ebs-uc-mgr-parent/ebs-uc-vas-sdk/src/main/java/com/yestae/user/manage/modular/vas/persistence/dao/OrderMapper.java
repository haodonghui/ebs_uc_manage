package com.yestae.user.manage.modular.vas.persistence.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.vas.persistence.model.Order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
  * 用户购买增值服务订单记录表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-24
 */
public interface OrderMapper extends BaseMapper<Order> {

	List<Map<String, Object>> selectOrderList(@Param("page") Page<Map<String, Object>> page, @Param("map") Map<String, Object> map);

	List<Map<String, Object>> selectOrderList(@Param("map") Map<String, Object> map);

}