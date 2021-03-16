package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.common.node.ZTreeNode;
import com.yestae.user.manage.modular.privilege.common.Constants.UserConstant;
import com.yestae.user.manage.modular.privilege.common.enums.QrcodeTypeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeGeneralizeChannelMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeGeneralizeUserMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeQrcodeMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeChannel;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeUser;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeChannelService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推广渠道表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
@Service
public class YestaeGeneralizeChannelServiceImpl extends ServiceImpl<YestaeGeneralizeChannelMapper, YestaeGeneralizeChannel> implements IYestaeGeneralizeChannelService {

	@Resource
	YestaeGeneralizeChannelMapper yestaeGeneralizeChannelMapper;
	
	@Resource
	YestaeGeneralizeUserMapper yestaeGeneralizeUserMapper;

	@Resource
	YestaeQrcodeMapper yestaeQrcodeMapper;
	
	@Override
	public List<Map<String, Object>> selectYestaeGeneralizeChannelList(Page<Map<String, Object>> page,
																	   Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		return yestaeGeneralizeChannelMapper.selectYestaeGeneralizeChannelList(page, map);
	}

	@Override
	public List<ZTreeNode> tree() {
		Integer ifDel = SysEnum.NO.getCode();
		return yestaeGeneralizeChannelMapper.tree(ifDel);
	}

	@Override
	public int insertYestaeGeneralizeChannel(YestaeGeneralizeChannel yestaeGeneralizeChannel) {
		if(yestaeGeneralizeChannel == null){
			return -1;
		}
		
		if(!this.checkChannelCode(yestaeGeneralizeChannel.getChannelCode(), null)){
			throw new BussinessException(BizExceptionEnum.CHANNEL_CODE_EXISTED);
		}
		
//		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
		
		yestaeGeneralizeChannel.setCreateTime(new Date().getTime());
//		yestaeGeneralizeChannel.setCreateBy(shiroUser.getId());
		yestaeGeneralizeChannel.setIfDel(SysEnum.NO.getCode());
		//根据pid设置是否末级（pid为null或pid不为"0"则不是末级）
		yestaeGeneralizeChannel.setIfEnd(yestaeGeneralizeChannel.getPid() == null || "0".equals(yestaeGeneralizeChannel.getPid())? SysEnum.NO.getCode(): SysEnum.YES.getCode());
		return yestaeGeneralizeChannelMapper.insert(yestaeGeneralizeChannel);
	}

	@Override
	public int updateYestaeGeneralizeChannel(YestaeGeneralizeChannel yestaeGeneralizeChannel) {
		if(yestaeGeneralizeChannel == null){
			return -1;
		}
		
		if(!this.checkChannelCode(yestaeGeneralizeChannel.getChannelCode(), yestaeGeneralizeChannel.getId())){
			throw new BussinessException(BizExceptionEnum.CHANNEL_CODE_EXISTED);
		}
		
		YestaeGeneralizeChannel yestaeGeneralizeChannelDb = yestaeGeneralizeChannelMapper.selectById(yestaeGeneralizeChannel.getId());
		
		if(yestaeGeneralizeChannelDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
//		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
		
		BeanUtils.copyProperties(yestaeGeneralizeChannel, yestaeGeneralizeChannelDb, "id",
				"createTime", "createBy", "ifDel");
		//根据pid设置是否末级（pid为null或pid不为"0"则不是末级）
		yestaeGeneralizeChannelDb.setIfEnd(yestaeGeneralizeChannel.getPid() == null || "0".equals(yestaeGeneralizeChannel.getPid())? SysEnum.NO.getCode(): SysEnum.YES.getCode());
//		yestaeGeneralizeChannelDb.setUpdateBy(shiroUser.getId());
		yestaeGeneralizeChannelDb.setUpdateTime(new Date().getTime());
		
		return yestaeGeneralizeChannelMapper.updateById(yestaeGeneralizeChannelDb);
	}
	
	/**
	 * 查询渠道编码是否存在
	 * @param channelCode
	 * @param id
	 * @return
	 */
	private boolean checkChannelCode(String channelCode, String id){

		QueryWrapper<YestaeGeneralizeChannel> wrapper = new QueryWrapper<>();
		wrapper.eq("if_del", SysEnum.NO.getCode());
		wrapper.eq("channel_code", channelCode);
		if(StringUtils.isNotEmpty(id)){
			wrapper.ne("id", id);
		}
		int num = yestaeGeneralizeChannelMapper.selectCount(wrapper);
		if(num > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 查询渠道编码是否存在
	 * @param channelCode
	 * @return
	 */
	public boolean checkChannelCode(String channelCode){
		
		return checkChannelCode(channelCode, null);
	}

	@Override
	public int deleteByYestaeGeneralizeChannelId(String yestaeGeneralizeChannelId) {
		
		//查询是否有关联的下级渠道，如果存在则不能删除
		QueryWrapper<YestaeGeneralizeChannel> wrapper = new QueryWrapper<>();
		wrapper.eq("if_del", SysEnum.NO.getCode());
		wrapper.eq("pid", yestaeGeneralizeChannelId);
		int num = yestaeGeneralizeChannelMapper.selectCount(wrapper);
		if(num > 0){
			throw new BussinessException(BizExceptionEnum.DELETE_CHANNEL_ERROE);
		}
		
//		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
		
		YestaeGeneralizeChannel yestaeGeneralizeChannelDb = yestaeGeneralizeChannelMapper.selectById(yestaeGeneralizeChannelId);
		
		if(yestaeGeneralizeChannelDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		yestaeGeneralizeChannelDb.setUpdateTime(new Date().getTime());
//		yestaeGeneralizeChannelDb.setUpdateBy(shiroUser.getId());
		yestaeGeneralizeChannelDb.setIfDel(SysEnum.YES.getCode());
		
		//删除推广渠道
		int i = yestaeGeneralizeChannelMapper.updateById(yestaeGeneralizeChannelDb);
		
		if(i > 0){
			Map<String, Object> map = new HashMap<>();
			map.put("ifDelYes", SysEnum.YES.getCode());
			map.put("ifDelNo", SysEnum.NO.getCode());
			map.put("updateTime", new Date().getTime());
//			map.put("updateBy", shiroUser.getId());
			
			//查询与推广渠道关联的推广人
			QueryWrapper<YestaeGeneralizeUser> YestaeGeneralizeUserWrapper = new QueryWrapper<>();
//			YestaeGeneralizeUserWrapper.setSqlSelect("id");
			YestaeGeneralizeUserWrapper.eq("if_del", SysEnum.NO.getCode());
			YestaeGeneralizeUserWrapper.eq("channel_id", yestaeGeneralizeChannelId);
			List<YestaeGeneralizeUser>  list = yestaeGeneralizeUserMapper.selectList(YestaeGeneralizeUserWrapper);
			
			//根据推广人list删除与推广人关联的二维码，list分割成若干子集合，子集合最多1000条数据，
			int j = (int) Math.ceil(list.size() / 1000.0);
			
			for(int k = 0; k < j; k++){
				
				int toIndex = (k + 1) * 1000 > list.size()? list.size(): (k + 1) * 1000;
				List<YestaeGeneralizeUser> innerList = list.subList(k * 1000, toIndex);
				map.put("generalizeUserList", innerList);
				i = yestaeQrcodeMapper.updateByMap(map);
				if(i < 0){
					throw new BussinessException(BizExceptionEnum.DELETE_ERROE);
				}
			}
			
			//循环推广人并删除与推广人关联的二维码
			/*for(YestaeGeneralizeUser yestaeGeneralizeUser: list){
				map.put("generalizeUserId", yestaeGeneralizeUser.getId());
				i = yestaeQrcodeMapper.updateByMap(map);
				if(i < 0){
					throw new BussinessException(BizExceptionEnum.DELETE_ERROE);
				}
			}*/
			
			map.put("channelId", yestaeGeneralizeChannelId);
			
			//删除与推广人
			i = yestaeGeneralizeUserMapper.updateByMap(map);
			if(i < 0){
				throw new BussinessException(BizExceptionEnum.DELETE_ERROE);
			}
		}
		
		return 1;
	}

	@Override
	public List<ZTreeNode> tree(String pid) {
		Integer ifDel = SysEnum.NO.getCode();
		return yestaeGeneralizeChannelMapper.tree(pid, ifDel);
	}
	
	@Override
	public YestaeGeneralizeChannel selectDcardChannel() {
		
		return selectChannelByQrcodeTypeEnum(QrcodeTypeEnum.DCARD);
	}
	
	@Override
	public YestaeGeneralizeChannel selectTeaTicketChannel() {
		
		return selectChannelByQrcodeTypeEnum(QrcodeTypeEnum.TEATICKET);
	}
	
	private YestaeGeneralizeChannel selectChannelByQrcodeTypeEnum(QrcodeTypeEnum qrcodeTypeEnum){
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("channel_code", qrcodeTypeEnum);
		columnMap.put("if_del", SysEnum.NO.getCode());
		List<YestaeGeneralizeChannel> list = yestaeGeneralizeChannelMapper.selectByMap(columnMap);
		if(list != null && list.size() > 0 && list.get(0) != null){
			return list.get(0);
		}else{
			YestaeGeneralizeChannel yestaeGeneralizeChannel = new YestaeGeneralizeChannel();
			yestaeGeneralizeChannel.setChannelCode(qrcodeTypeEnum.toString());
			yestaeGeneralizeChannel.setName(qrcodeTypeEnum.getMessage());
			yestaeGeneralizeChannel.setPid("0");
			insertYestaeGeneralizeChannel(yestaeGeneralizeChannel);
			return yestaeGeneralizeChannel;
		}
	}

	@Override
	public List<Map<String, Object>> selectYestaeGeneralizeChannelListDYCT(Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		if(StringUtils.isEmpty(MapUtils.getString(map, "pcode"))){
			map.put("pcode", UserConstant.CHANNEL_CODE_DYCT);
		}
		return yestaeGeneralizeChannelMapper.selectYestaeGeneralizeChannelList(map);
	}
}
