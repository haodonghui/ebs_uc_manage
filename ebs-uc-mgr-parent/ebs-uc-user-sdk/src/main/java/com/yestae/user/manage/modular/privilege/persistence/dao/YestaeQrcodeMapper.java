package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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