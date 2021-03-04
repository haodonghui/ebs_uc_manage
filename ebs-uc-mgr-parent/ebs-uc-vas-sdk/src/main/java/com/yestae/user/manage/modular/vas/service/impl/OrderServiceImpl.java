package com.yestae.user.manage.modular.vas.service.impl;

import com.yestae.user.manage.modular.vas.persistence.model.Order;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.dao.OrderMapper;
import com.yestae.user.manage.modular.vas.persistence.dao.StoreMapper;
import com.yestae.user.manage.modular.vas.service.IOrderService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户购买增值服务订单记录表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

	private final Logger logger = LoggerFactory.getLogger(EquityServiceImpl.class);
	@Resource
	OrderMapper orderMapper;
	
	//列表
	@Override
	public List<Map<String, Object>> selectList(Page<Map<String, Object>> page, Map<String, Object> map) {
		// TODO Auto-generated method stub
		map.put("delFlag", VasConstants.YES);
		if(page == null){
			return orderMapper.selectOrderList(map);
		}
		return orderMapper.selectOrderList(page, map);
	}
	
}
