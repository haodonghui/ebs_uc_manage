package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.Record;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户购买增值服务记录表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-25
 */
public interface IRecordService extends IService<Record> {

	List<Map<String, Object>> selectRecordList(Page<Map<String, Object>> page, Map<String, Object> paramMap);

	
}
