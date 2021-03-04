package com.yestae.user.manage.modular.vas.persistence.dao;

import com.yestae.user.manage.modular.vas.persistence.model.Record;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 用户购买增值服务记录表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-25
 */
public interface RecordMapper extends BaseMapper<Record> {

	List<Map<String, Object>> selectRecordList(@Param("page")Page<Map<String, Object>> page, @Param("map") Map<String, Object> map);

}