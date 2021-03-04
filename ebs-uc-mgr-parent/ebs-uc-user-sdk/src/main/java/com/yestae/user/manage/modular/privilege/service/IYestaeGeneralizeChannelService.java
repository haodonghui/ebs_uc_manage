package com.yestae.user.manage.modular.privilege.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.yestae.user.common.node.ZTreeNode;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeChannel;

/**
 * <p>
 * 推广渠道表 服务类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
public interface IYestaeGeneralizeChannelService extends IService<YestaeGeneralizeChannel> {

	/**
	 * 查询推广渠道列表 
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectYestaeGeneralizeChannelList(Page<Map<String, Object>> page,
			Map<String, String> map);

	/**
	 * 获取推广渠道tree列表 
	 * @return
	 */
	List<ZTreeNode> tree();

	/**
	 * 新增推广渠道
	 * @param yestaeGeneralizeChannel
	 * @return
	 */
	int insertYestaeGeneralizeChannel(YestaeGeneralizeChannel yestaeGeneralizeChannel);

	/**
	 * 修改推广渠道
	 * @param yestaeGeneralizeChannel
	 * @return
	 */
	int updateYestaeGeneralizeChannel(YestaeGeneralizeChannel yestaeGeneralizeChannel);

	/**
	 * 删除推广渠道
	 * @param yestaeGeneralizeChannelId
	 * @return
	 */
	int deleteByYestaeGeneralizeChannelId(String yestaeGeneralizeChannelId);

	/**
	 * 根据父节点查询tree
	 * @param pid
	 * @return
	 */
	List<ZTreeNode> tree(String pid);
	
	/**
	 * 查找钻卡渠道
	 * @return
	 */
	YestaeGeneralizeChannel selectDcardChannel();
	/**
	 * 查找茶票渠道
	 * @return
	 */
	YestaeGeneralizeChannel selectTeaTicketChannel();
	
	/**
	 * 查询渠道编码是否存在
	 * @param channelCode
	 * @return
	 */
	boolean checkChannelCode(String channelCode);

	/**
     * 获取大益茶庭子推广渠道列表
     */
	List<Map<String, Object>> selectYestaeGeneralizeChannelListDYCT(Map<String, String> map);
}
