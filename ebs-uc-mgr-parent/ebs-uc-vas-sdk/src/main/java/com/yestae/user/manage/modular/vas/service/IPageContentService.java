package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;

/**
 * <p>
 * 页面内容表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
public interface IPageContentService extends IService<PageContent> {

	/**
	 * 添加或修改页面内容
	 * @param pageContent
	 */
	void saveOrUpdate(PageContent pageContent);
	/**
	 * 查询图片
	 * @param bizId
	 * @param BizType
	 * @return
	 */
	public PageContent findOnePageContent(String bizId, Integer bizType);
}
