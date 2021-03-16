package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcode;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二维码表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-05
 */
public interface IYestaeQrcodeService extends IService<YestaeQrcode> {

	/**
	 * 查询二维码列表
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeQrcodeList(Page<Map<String, Object>> page, Map<String, String> map);

	/**
	 * 新增二维码
	 * @param yestaeQrcode
	 * @return
	 */
	int insertYestaeQrcode(YestaeQrcode yestaeQrcode, String imageDir, String weixinQrCodeDir, String page);

	/**
	 * 系统自动生成二维码
	 * @param yestaeQrcode
	 * @return
	 */
	int insertYestaeQrcodeAuto(YestaeQrcode yestaeQrcode, String imageDir, String weixinQrCodeDir);
	
	/**
	 * 修改二维码
	 * @param yestaeQrcode
	 * @return
	 */
	int updateYestaeQrcode(YestaeQrcode yestaeQrcode);
	
}
