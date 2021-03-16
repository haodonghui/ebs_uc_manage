package com.yestae.user.manage.modular.weixin.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yestae.user.manage.modular.weixin.persistence.model.WeiXin;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

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