package com.yestae.user.manage.modular.vas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.vas.persistence.dao.VasImageMapper;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 增值服务图片 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@Service
public class VasImageServiceImpl extends ServiceImpl<VasImageMapper, VasImage> implements IVasImageService {
	
	private final Logger logger = LoggerFactory.getLogger(VasImageServiceImpl.class);
	
	@Override
	public void updateVasImage(String vasImageId, String bizId) {
		logger.info("VasImageServiceImpl->updateVasImage->vasImageId:{}, bizId:{}", vasImageId, bizId);
		if(!StringUtils.isEmpty(vasImageId) && !StringUtils.isEmpty(bizId)){
			VasImage vasImage = this.getById(vasImageId);
			if(vasImage != null && !bizId.equals(vasImage.getBizId())){
				
				VasImage vasImageDb = findOneVasImage(bizId, vasImage.getBizType());
				if(vasImageDb != null){
					this.removeById(vasImageDb.getId());
				}
				
				vasImage.setBizId(bizId);
				vasImage.setUpdateTime(new Date().getTime());
				this.updateById(vasImage);
			}
		}
	}

	@Override
	public VasImage findOneVasImage(String bizId, Integer bizType) {
		logger.info("VasImageServiceImpl->findOneVasImage->bizId:{}, BizType:{}", bizId, bizType);
		if(StringUtils.isEmpty(bizId) || bizType == null){
			return null;
		}
		QueryWrapper<VasImage> wrapper = new QueryWrapper<>();
		wrapper.eq("biz_id", bizId);
		wrapper.eq("biz_type", bizType);
		List<VasImage> list = this.list(wrapper);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
