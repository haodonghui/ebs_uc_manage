package com.yestae.user.manage.modular.privilege.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.node.ZTreeNode;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeChannel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 推广渠道表 Mapper 接口
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
public interface YestaeGeneralizeChannelMapper extends BaseMapper<YestaeGeneralizeChannel> {

	List<Map<String, Object>> selectYestaeGeneralizeChannelList(@Param("page") Page<Map<String, Object>> page,
			@Param("map") Map<String, String> map);

	List<ZTreeNode> tree(@Param("ifDel") Integer ifDel);

	List<ZTreeNode> tree(@Param("pid") String pid, @Param("ifDel") Integer ifDel);

	List<Map<String, Object>> selectYestaeGeneralizeChannelList(@Param("map") Map<String, String> map);

}