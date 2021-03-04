package com.yestae.user.manage.modular.vas.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.vas.persistence.dao.VasImageMapper;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IVasImageService;

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
			VasImage vasImage = this.selectById(vasImageId);
			if(vasImage != null && !bizId.equals(vasImage.getBizId())){
				
				VasImage vasImageDb = findOneVasImage(bizId, vasImage.getBizType());
				if(vasImageDb != null){
					this.deleteById(vasImageDb.getId());
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
		Wrapper<VasImage> wrapper = new EntityWrapper<>();
		wrapper.eq("biz_id", bizId);
		wrapper.eq("biz_type", bizType);
		List<VasImage> list = this.selectList(wrapper);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
