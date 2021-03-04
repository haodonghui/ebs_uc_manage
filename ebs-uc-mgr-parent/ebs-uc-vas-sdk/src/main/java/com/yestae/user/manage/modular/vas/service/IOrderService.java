package com.yestae.user.manage.modular.vas.service;

import com.yestae.user.manage.modular.vas.persistence.model.Order;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户购买增值服务订单记录表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-24
 */
public interface IOrderService extends IService<Order> {

	List<Map<String, Object>> selectList(Page<Map<String, Object>> page, Map<String, Object> paramMap);
	
}
