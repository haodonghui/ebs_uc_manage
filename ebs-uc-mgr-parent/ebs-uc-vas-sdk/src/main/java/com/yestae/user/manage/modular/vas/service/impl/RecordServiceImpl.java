package com.yestae.user.manage.modular.vas.service.impl;

import com.yestae.user.manage.modular.vas.persistence.model.Record;
import com.yestae.user.manage.modular.vas.persistence.dao.RecordMapper;
import com.yestae.user.manage.modular.vas.persistence.dao.StoreMapper;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IRecordService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户购买增值服务记录表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-25
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

	//private final Logger logger = LoggerFactory.getLogger(EquityServiceImpl.class);
	@Resource
	RecordMapper recordMapper;
	
	
	//用户增值列表
	@Override
	public List<Map<String, Object>> selectRecordList(Page<Map<String, Object>> page, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return recordMapper.selectRecordList(page,map);
	}
	
}
