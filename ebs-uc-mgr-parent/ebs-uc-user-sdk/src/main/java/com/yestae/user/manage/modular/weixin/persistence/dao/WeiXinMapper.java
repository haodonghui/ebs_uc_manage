package com.yestae.user.manage.modular.weixin.persistence.dao;

import com.yestae.user.manage.modular.weixin.persistence.model.WeiXin;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 微信表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-11
 */

public interface WeiXinMapper extends BaseMapper<WeiXin> {
	WeiXin selectByCode(@Param("code") String code);

	int updateByMap(@Param("map") Map<String, Object> map);

}