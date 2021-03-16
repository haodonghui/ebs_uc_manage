package com.yestae.user.manage.modular.vas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.dao.OrganizMapper;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IPageContentService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@Service
public class OrganizServiceImpl extends ServiceImpl<OrganizMapper, Organiz> implements IOrganizService {

	private final Logger logger = LoggerFactory.getLogger(OrganizServiceImpl.class);
	
	@Resource
	IVasImageService vasImageService; 
	@Resource
	IPageContentService pageContentService; 
	
	@Override
	public void insertOrganiz(Organiz organiz) {
		logger.info("OrganizServiceImpl->insertOrganiz->organiz:{}", JSONObject.toJSON(organiz));
		if(organiz != null){
			//名称校验
			QueryWrapper<Organiz> wrapper1 = new QueryWrapper<>();
			wrapper1.eq("organiz_name", organiz.getOrganizName());
			int count1 = this.count(wrapper1);
			if(count1 > 0){
				throw new BussinessException(BizExceptionEnum.ORGANIZ_NAME_EXISTED);
			}
			
			organiz.setDelFlag(VasConstants.YES);
			organiz.setStatus(VasConstants.STATUS_ON);
			this.save(organiz);
			
			vasImageService.updateVasImage(organiz.getLogo(), organiz.getId());
			vasImageService.updateVasImage(organiz.getSurfaceId(), organiz.getId());
			vasImageService.updateVasImage(organiz.getOrganizLogo(), organiz.getId());
			vasImageService.updateVasImage(organiz.getOrganizLogoInvalid(), organiz.getId());
			
			PageContent pageContent = new PageContent();
			pageContent.setBizId(organiz.getId());
			pageContent.setBizType(VasConstants.PC_BIZ_TYPE_ORGANIZ_STORY);
			pageContent.setContent(organiz.getContent());
			pageContent.setContentType(VasConstants.PC_CONTENT_TYPE_URL);
			pageContent.setCreateBy(organiz.getCreateBy());
			pageContentService.saveOrUpdate(pageContent);
		}else{
			logger.error("OrganizServiceImpl->insertOrganiz->organiz:{}", JSONObject.toJSON(organiz));
		}
	}

	@Override
	public void updateOrganiz(Organiz organiz) {
		logger.info("OrganizServiceImpl->updateOrganiz->organiz:{}", JSONObject.toJSON(organiz));
		if(organiz != null){
			//名称校验
			QueryWrapper<Organiz> wrapper2 = new QueryWrapper<>();
			wrapper2.eq("organiz_name", organiz.getOrganizName());
			wrapper2.ne("id", organiz.getId());
			int count2 = this.count(wrapper2);
			if(count2 > 0){
				throw new BussinessException(BizExceptionEnum.ORGANIZ_NAME_EXISTED);
			}
			
			Organiz organizDb = this.getById(organiz.getId());
			if(organizDb == null){
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			BeanUtils.copyProperties(organiz, organizDb, new String[]{"createTime", "createBy", "delFlag", "organizeCode", "status"});
			this.updateById(organizDb);
			
			vasImageService.updateVasImage(organiz.getLogo(), organiz.getId());
			vasImageService.updateVasImage(organiz.getSurfaceId(), organiz.getId());
			vasImageService.updateVasImage(organiz.getOrganizLogo(), organiz.getId());
			vasImageService.updateVasImage(organiz.getOrganizLogoInvalid(), organiz.getId());
			
			PageContent pageContent = new PageContent();
			pageContent.setBizId(organizDb.getId());
			pageContent.setBizType(VasConstants.PC_BIZ_TYPE_ORGANIZ_STORY);
			pageContent.setContent(organizDb.getContent());
			pageContent.setContentType(VasConstants.PC_CONTENT_TYPE_URL);
			pageContent.setUpdateBy(organizDb.getUpdateBy());
			pageContentService.saveOrUpdate(pageContent);
		}else{
			logger.error("OrganizServiceImpl->updateOrganiz->organiz:{}", JSONObject.toJSON(organiz));
		}
	}
	
}
