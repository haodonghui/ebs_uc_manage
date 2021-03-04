package com.yestae.user.manage.modular.privilege.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyPhoneLog;

/**
 * <p>
  * 用户手机号变更日志 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface YestaeUserModifyPhoneLogMapper extends BaseMapper<YestaeUserModifyPhoneLog> {

	List<Map<String, Object>> selectYestaeUserModifyPhoneLogList(@Param("page") Page<Map<String, Object>> page,
			@Param("map") Map<String, String> map);

}