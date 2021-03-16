package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyGradeLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户会员等级变更日志 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
public interface YestaeUserModifyGradeLogMapper extends BaseMapper<YestaeUserModifyGradeLog> {

	List<Map<String, Object>> selectYestaeUserModifyGradeLogList(@Param("page") Page<Map<String, Object>> page,
			@Param("map") Map<String, String> map);

}