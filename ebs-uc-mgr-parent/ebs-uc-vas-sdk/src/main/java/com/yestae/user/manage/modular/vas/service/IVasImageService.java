package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;

/**
 * <p>
 * 增值服务图片 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
public interface IVasImageService extends IService<VasImage> {
	
	/**
	 * 更新图片
	 * @param vasImageId
	 * @param bizId
	 */
	public void updateVasImage(String vasImageId, String bizId);

	/**
	 * 查询图片
	 * @param bizId
	 * @param bizType
	 * @return
	 */
	public VasImage findOneVasImage(String bizId, Integer bizType);
}
