package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcodeScene;

/**
 * <p>
 * 二维码场景表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-04
 */
public interface IYestaeQrcodeSceneService extends IService<YestaeQrcodeScene> {

	/**
	 * 修改二维码场景
	 * @param yestaeQrcodeScene
	 * @return
	 */
	int updateYestaeQrcodeScene(YestaeQrcodeScene yestaeQrcodeScene);

	/**
	 * 删除二维码场景
	 * @param yestaeQrcodeSceneId
	 * @return
	 */
	int deleteByYestaeQrcodeSceneId(String yestaeQrcodeSceneId);
	
	/**
	 * 查找钻卡二维码场景
	 * @return
	 */
	YestaeQrcodeScene selectDcardYestaeQrcodeScene();
	
	/**
	 * 查找茶票二维码场景
	 * @return
	 */
	YestaeQrcodeScene selectTeaTicketYestaeQrcodeScene();
	
}
