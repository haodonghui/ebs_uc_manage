package com.yestae.user.manage.modular.privilege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yestae.user.common.util.Convert;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.UserAccountFlowMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.UserAccountMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccount;
import com.yestae.user.manage.modular.privilege.service.IUserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户账户信息 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

	@Resource
	UserAccountMapper userAccountMapper;
	
	@Resource
	UserAccountFlowMapper userAccountFlowMapper;
	
	@Override
	public List<Map<String, Object>> selectUserAccountList(Page<Map<String, Object>> page, Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		if(page != null){
			return userAccountMapper.selectUserAccountList(page, map);
		}else{
			return userAccountMapper.selectUserAccountList(map);
		}
	}
	@Override
	@Transactional
	public int updateUserAccount(Map<String, String> map) {
		/*
		
		ShiroUser shiroUser = (ShiroUser) HttpKit.getRequest().getSession().getAttribute("shiroUser");
		
		String id = MapUtils.getString(map, "id");
		String type = MapUtils.getString(map, "type");
		String amount = MapUtils.getString(map, "amount");
		String remark = MapUtils.getString(map, "remark");
		String updateTime = MapUtils.getString(map, "updateTime");
		
		//校验数据有效性
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(type) || 
				StringUtils.isEmpty(updateTime) || 
				StringUtils.isEmpty(amount) || StringUtils.isEmpty(remark) ||
				StringUtils.isEmpty(AccountFlowTypeEnum.valueOf(Convert.toInt(type))) ||
				Convert.toBigDecimal(amount) == null || 
				Convert.toBigDecimal(amount).compareTo(Convert.toBigDecimal("0")) <= 0){
			throw new BussinessException(BizExceptionEnum.REQUEST_INVALIDATE);
		}
		
		//查询用户账户
		UserAccount userAccount = userAccountMapper.selectById(id);
		if(userAccount == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		//交易前余额
		BigDecimal preAmount = userAccount.getAmount();
		if(preAmount == null){
			preAmount = Convert.toBigDecimal("0");
		}
		//交易后余额
		BigDecimal laterAmount = null;
		//变更金额
		BigDecimal changeAmount = Convert.toBigDecimal(amount);
		
		//减少
		if(AccountFlowTypeEnum.DECREASE.getCode() == Convert.toInt(type)){
			laterAmount = preAmount.subtract(changeAmount);
			//余额不足
			if(laterAmount.compareTo(Convert.toBigDecimal("0")) < 0){
				throw new BussinessException(BizExceptionEnum.NOT_SUFFICIENT_FUNDS);
			}
		}else{
		//增加
			laterAmount = preAmount.add(changeAmount);
			//账户超额
			if(laterAmount.compareTo(Convert.toBigDecimal("10000000")) >= 0){
				throw new BussinessException(BizExceptionEnum.OVERFLOW_FUNDS);
			}
		}
		
		//添加金额变动流水
		UserAccountFlow userAccountFlow = new UserAccountFlow();
		userAccountFlow.setAccountId(userAccount.getId());
		userAccountFlow.setUserId(userAccount.getUserId());
		userAccountFlow.setType(type);
		userAccountFlow.setAmount(changeAmount);
		userAccountFlow.setPreAmount(preAmount);
		userAccountFlow.setLaterAmount(laterAmount);
		userAccountFlow.setRemark(remark);
		userAccountFlow.setCreateMonth(DateUtils.getMonth());
		userAccountFlow.setCreateYear(DateUtils.getYear());
		userAccountFlow.setCreateTime(new Date().getTime());
		userAccountFlow.setCreateBy(shiroUser.getId());
		
		userAccountFlowMapper.insert(userAccountFlow);
		
		//userAccount.setAmount(laterAmount);
		
		//重新查询用户账户
		UserAccount userAccountDb = userAccountMapper.selectById(id);
		
		//1.处于冻结状态 2.未删除 3.最后一次修改时间与数据库保持一致
		if((AccountStateEnum.FREEZE.getCode() + "").equals(userAccount.getState())
				&& (SysEnum.NO.getCode() + "").equals(userAccount.getIfDel())
				&& updateTime.equals(userAccountDb.getUpdateTime().toString())){
			userAccountDb.setAmount(laterAmount);
			userAccountDb.setUpdateTime(new Date().getTime());
			userAccountMapper.updateById(userAccountDb);
		}else{
			throw new BussinessException(BizExceptionEnum.DATABASE_CHANGED);
		}
		
		 */
		return 1;
	}
	public static void main(String[] args) {
		BigDecimal preAmount = Convert.toBigDecimal("1.2");
		BigDecimal changeAmount = Convert.toBigDecimal("2.3");
		
		System.out.println(preAmount.subtract(changeAmount));
	}
}
