package com.yestae.user.manage.modular.vas.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.dao.PageContentMapper;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;
import com.yestae.user.manage.modular.vas.service.IPageContentService;

/**
 * <p>
 * 页面内容表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@Service
public class PageContentServiceImpl extends ServiceImpl<PageContentMapper, PageContent> implements IPageContentService {

	private final Logger logger = LoggerFactory.getLogger(PageContentServiceImpl.class);
	
	@Override
	public void saveOrUpdate(PageContent pageContent) {
		logger.info("PageContentServiceImpl->saveOrUpdate->pageContent:{}", JSONObject.toJSON(pageContent));
		if(pageContent != null){
			PageContent pageContentDb = findOnePageContent(pageContent.getBizId(), pageContent.getBizType());
			if(pageContentDb != null){
				BeanUtils.copyProperties(pageContent, pageContentDb, new String[]{"id", "createBy", "createTime", "delFlag"});
				pageContentDb.setUpdateTime(new Date().getTime());
				this.updateById(pageContentDb);
			}else{
				if(!StringUtils.isEmpty(pageContent.getContent())){
					pageContent.setDelFlag(VasConstants.YES);
					pageContent.setCreateTime(new Date().getTime());
					this.insert(pageContent);
				}
			}
		}
	}

	@Override
	public PageContent findOnePageContent(String bizId, Integer bizType) {
		logger.info("PageContentServiceImpl->findOnePageContent->bizId:{}, BizType:{}", bizId, bizType);
		if(StringUtils.isEmpty(bizId) || bizType == null){
			return null;
		}
		Wrapper<PageContent> wrapper = new EntityWrapper<>();
		wrapper.eq("biz_id", bizId);
		wrapper.eq("biz_type", bizType);
		List<PageContent> list = this.selectList(wrapper);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
