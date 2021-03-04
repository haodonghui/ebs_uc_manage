package com.yestae.user.manage.modular.vas.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.common.enums.PageContentBizTypeEnum;
import com.yestae.user.manage.modular.vas.persistence.dao.VasMapper;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;
import com.yestae.user.manage.modular.vas.persistence.model.OrganizVas;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;
import com.yestae.user.manage.modular.vas.persistence.model.Store;
import com.yestae.user.manage.modular.vas.persistence.model.Vas;
import com.yestae.user.manage.modular.vas.persistence.model.VasEquity;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IPageContentService;
import com.yestae.user.manage.modular.vas.service.IVasEquityService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import com.yestae.user.manage.modular.vas.service.IVasService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
@Service
public class VasServiceImpl extends ServiceImpl<VasMapper, Vas> implements IVasService {

	@Resource
	VasMapper vasMapper;
	@Resource
	IPageContentService pageContentService; 
	@Autowired
    private IVasEquityService vasEquityService;
	@Autowired
	private IOrganizService organizService;
	@Resource
	IVasImageService vasImageService; 
	
	@Override
	public List<OrganizVas> findAllOrganizVas(Map<String, Object> map) {
		return vasMapper.findOrganizVas(map);
	}

	@Override
	public List<Map<String, Object>> selectVasList(Page<Map<String, Object>> page, Map<String, Object> map) {
		map.put("delFlag", VasConstants.YES);
		return vasMapper.selectVasList(page, map);
	}

	@Override
	@Transactional
	public void insertVas(Vas vas) {
		if(vas != null){
			//校验服务名称
			Wrapper<Vas> wrapper1 = new EntityWrapper<>();
			wrapper1.eq("vas_name", vas.getVasName());
			wrapper1.eq("organiz_id", vas.getOrganizId());
			wrapper1.eq("del_flag", VasConstants.YES);
			int count1 = this.selectCount(wrapper1);
			if(count1 > 0){
				throw new BussinessException(BizExceptionEnum.ORGANIZ_VAS_NAME_EXISTED);
			}
			
			vas.setDelFlag(VasConstants.YES);
			vas.setStatus(VasConstants.STATUS_ON);
			
			//添加机构编码
			if(!StringUtils.isEmpty(vas.getOrganizId())){
				Organiz organiz = organizService.selectById(vas.getOrganizId());
				if(organiz != null){
					vas.setOrganizCode(organiz.getOrganizCode());
				}
			}
			
			//添加增值服务
			this.insert(vas);
			
			//添加增值服务图片
			vasImageService.updateVasImage(vas.getSurfaceId(), vas.getId());
			
			//添加增值服务关联权益
			this.insertVasEquity(vas);
			
			//添加权益详情
			if(!StringUtils.isEmpty(vas.getEquityDetail())){
				PageContent pageContent = new PageContent();
				pageContent.setBizId(vas.getId());
				pageContent.setBizType(PageContentBizTypeEnum.PC_BIZ_TYPE_EQUITY_DETAIL.getCode());
				pageContent.setTitle(PageContentBizTypeEnum.PC_BIZ_TYPE_EQUITY_DETAIL.getMessage());
				pageContent.setContent(vas.getEquityDetail());
				pageContent.setContentType(VasConstants.PC_CONTENT_TYPE_TEXT);
				pageContent.setCreateBy(vas.getCreateBy());
				pageContentService.saveOrUpdate(pageContent);
			}
			//添加常见问题
			if(!StringUtils.isEmpty(vas.getFaq())){
				PageContent pageContent = new PageContent();
				pageContent.setBizId(vas.getId());
				pageContent.setBizType(PageContentBizTypeEnum.PC_BIZ_TYPE_FAQ.getCode());
				pageContent.setTitle(PageContentBizTypeEnum.PC_BIZ_TYPE_FAQ.getMessage());
				pageContent.setContent(vas.getFaq());
				pageContent.setContentType(VasConstants.PC_CONTENT_TYPE_TEXT);
				pageContent.setCreateBy(vas.getCreateBy());
				pageContentService.saveOrUpdate(pageContent);
			}
			//添加服务条款
			if(!StringUtils.isEmpty(vas.getServiceTerms())){
				PageContent pageContent = new PageContent();
				pageContent.setBizId(vas.getId());
				pageContent.setBizType(PageContentBizTypeEnum.PC_BIZ_TYPE_SERVICE_TERMS.getCode());
				pageContent.setTitle(PageContentBizTypeEnum.PC_BIZ_TYPE_SERVICE_TERMS.getMessage());
				pageContent.setContent(vas.getServiceTerms());
				pageContent.setContentType(VasConstants.PC_CONTENT_TYPE_TEXT);
				pageContent.setCreateBy(vas.getCreateBy());
				pageContentService.saveOrUpdate(pageContent);
			}
		}
	}

	@Override
	@Transactional
	public void updateVas(Vas vas) {
		if(vas != null){
			//校验服务名称
			Wrapper<Vas> wrapper2 = new EntityWrapper<>();
			wrapper2.eq("vas_name", vas.getVasName());
			wrapper2.eq("organiz_id", vas.getOrganizId());
			wrapper2.eq("del_flag", VasConstants.YES);
			wrapper2.ne("id", vas.getId());
			int count2 = this.selectCount(wrapper2);
			if(count2 > 0){
				throw new BussinessException(BizExceptionEnum.ORGANIZ_VAS_NAME_EXISTED);
			}
			
			Vas vasDb = this.selectById(vas.getId());
			if(vasDb == null){
				throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
			}
			
			//添加机构编码
			if(vas.getOrganizId() != null && !vas.getOrganizId().equals(vasDb.getOrganizId())){
				Organiz organiz = organizService.selectById(vas.getOrganizId());
				if(organiz != null){
					vas.setOrganizCode(organiz.getOrganizCode());
				}
			}
			
			BeanUtils.copyProperties(vas, vasDb, new String[]{"createTime", "createBy", "delFlag", "vasCode", "status"});
			
			//修改增值服务
			this.updateById(vasDb);
			
			//修改增值服务图片
			vasImageService.updateVasImage(vas.getSurfaceId(), vas.getId());
			
			//删除关联权益
			Wrapper<VasEquity> wrapper = new EntityWrapper<>();
			wrapper.eq("vas_id", vas.getId());
			vasEquityService.delete(wrapper);
			
			//添加关联权益
			this.insertVasEquity(vas);
			
			//添加权益详情
			PageContent pageContent = new PageContent();
			pageContent.setBizId(vas.getId());
			pageContent.setBizType(PageContentBizTypeEnum.PC_BIZ_TYPE_EQUITY_DETAIL.getCode());
			pageContent.setTitle(PageContentBizTypeEnum.PC_BIZ_TYPE_EQUITY_DETAIL.getMessage());
			pageContent.setContent(vas.getEquityDetail());
			pageContent.setContentType(VasConstants.PC_CONTENT_TYPE_TEXT);
			pageContent.setCreateBy(vas.getCreateBy());
			pageContentService.saveOrUpdate(pageContent);

			//添加常见问题
			PageContent pageContentFaq = new PageContent();
			pageContentFaq.setBizId(vas.getId());
			pageContentFaq.setBizType(PageContentBizTypeEnum.PC_BIZ_TYPE_FAQ.getCode());
			pageContentFaq.setTitle(PageContentBizTypeEnum.PC_BIZ_TYPE_FAQ.getMessage());
			pageContentFaq.setContent(vas.getFaq());
			pageContentFaq.setContentType(VasConstants.PC_CONTENT_TYPE_TEXT);
			pageContentFaq.setCreateBy(vas.getCreateBy());
			pageContentService.saveOrUpdate(pageContentFaq);
			
			//添加服务 条款
			PageContent pageContentSt = new PageContent();
			pageContentSt.setBizId(vas.getId());
			pageContentSt.setBizType(PageContentBizTypeEnum.PC_BIZ_TYPE_SERVICE_TERMS.getCode());
			pageContentSt.setTitle(PageContentBizTypeEnum.PC_BIZ_TYPE_SERVICE_TERMS.getMessage());
			pageContentSt.setContent(vas.getServiceTerms());
			pageContentSt.setContentType(VasConstants.PC_CONTENT_TYPE_TEXT);
			pageContentSt.setCreateBy(vas.getCreateBy());
			pageContentService.saveOrUpdate(pageContentSt);
		}
	}
	
	/**
	 * 添加增值服务权益
	 * @param vas
	 */
	private void insertVasEquity(Vas vas){
		List<VasEquity> vasEquityList = vas.getVasEquityList();
		if(vasEquityList != null){
			
			for(VasEquity vasEquity: vasEquityList){
				vasEquity.setCreateBy(vas.getUpdateBy());
				vasEquity.setCreateTime(new Date().getTime());
				vasEquity.setVasCode(vas.getVasCode());
				vasEquity.setVasId(vas.getId());
			}
			vasEquityService.insertBatch(vasEquityList);
		}
	}
}
