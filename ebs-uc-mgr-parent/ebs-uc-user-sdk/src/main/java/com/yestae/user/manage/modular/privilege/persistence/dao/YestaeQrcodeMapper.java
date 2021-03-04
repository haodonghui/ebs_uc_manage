package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcode;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 二维码表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-05
 */
public interface YestaeQrcodeMapper extends BaseMapper<YestaeQrcode> {

	List<Map<String, Object>> selectYestaeQrcodeList(@Param("page") Page<Map<String, Object>> page, 
			@Param("map") Map<String, String> map);

	int updateByMap(@Param("map") Map<String, Object> map);

}