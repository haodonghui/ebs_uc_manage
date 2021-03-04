package com.yestae.user.manage.modular.privilege.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yestae.user.center.dubbo.entity.UserRealNameCertificationDubbo;
import com.yestae.user.center.dubbo.service.IUserCenterRealNameAuthenticationService;
import com.yestae.user.manage.config.HttpSmsConfig;
import com.yestae.user.manage.utils.HttpClientUtils;
import com.yestae.user.manage.utils.ValidCodeDto;
import com.yestae.user.manage.utils.WebUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.sms.api.dubbo.ISmsDubboService;
import com.yestae.sms.api.dubbo.SmsResult;
import com.yestae.sms.enums.SmsTypeEnum;
import com.yestae.user.center.dubbo.entity.UserResult;
import com.yestae.user.center.dubbo.service.IUserCenterService;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.annotion.BussinessLog;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.base.tips.ErrorTip;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroUser;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.DefaultAddressEnum;
import com.yestae.user.manage.modular.privilege.common.enums.GenderEnum;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.common.enums.UserStatusEnum;
import com.yestae.user.manage.modular.privilege.common.enums.UserTypeEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUser;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserAddress;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserImage;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserAddressService;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserImageService;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserService;
import com.yestae.user.manage.modular.privilege.vo.YestaeUserVo;

/**
 * 用户控制器
 *
 * @author fengshuonan
 * @Date 2017-11-03 15:59:08
 */
@Controller
@RequestMapping("/yestaeUser")
public class YestaeUserController extends BaseController {

    private String PREFIX = "/privilege/yestaeUser/";

    @Resource
	UcConstant ucConstant;

    @Autowired
    private IYestaeUserService yestaeUserService;

    @Autowired
    private IYestaeUserAddressService yestaeUserAddressService;

    @Autowired
    private IYestaeUserImageService yestaeUserImageService;

    @Resource
	private IUserCenterService userCenterService;

    @Autowired
	private ISmsDubboService smsDubboService;

	@Resource
	private IUserCenterRealNameAuthenticationService userCenterRealNameAuthenticationService;

	@Resource
	private HttpSmsConfig httpSmsConfig;

    /**
     * 跳转到用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUser.html";
    }

    /**
     * 跳转到用户选择列表
     */
    @RequestMapping("yestaeUser_select")
    public String yestaeUserSelect() {
    	return PREFIX + "yestaeUser_select.html";
    }

    /**
     * 跳转到添加用户
     */
    @RequestMapping("/yestaeUser_add")
    public String yestaeUserAdd() {
        return PREFIX + "yestaeUser_add.html";
    }

    /**
     * 跳转到修改用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUser_update/{yestaeUserId}")
    public String yestaeUserUpdate(@PathVariable String yestaeUserId, Model model) {
        YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserId);
        YestaeUserVo yestaeUserVo = new YestaeUserVo();
        YestaeUserAddress yestaeUserAddress = new YestaeUserAddress();
        if(yestaeUser != null){
        	yestaeUserVo.setId(yestaeUser.getId());
        	yestaeUserVo.setStatus(yestaeUser.getStatus());
        	yestaeUserVo.setName(yestaeUser.getName());
        	yestaeUserVo.setMobile(yestaeUser.getMobile());
        	yestaeUserVo.setGender(yestaeUser.getGender());
        	yestaeUserVo.setSource(yestaeUser.getSource());
        	yestaeUserVo.setCreateTime(DateUtil.getTime(yestaeUser.getCreateTime()));
        	yestaeUserVo.setBirthday(DateUtils.getTimeString(yestaeUser.getBirthday()));
        	yestaeUserVo.setTeaTeacherLevel(yestaeUser.getTeaTeacherLevel());
        	yestaeUserVo.setCardNo(yestaeUser.getCardNo());
        	yestaeUserVo.setType(yestaeUser.getType());
        	yestaeUserVo.setUpgradeTime(DateUtils.toDatetimeString(yestaeUser.getUpgradeTime()));

        	EntityWrapper<YestaeUserAddress> yestaeUserAddressWrapper = new EntityWrapper<YestaeUserAddress>();
        	yestaeUserAddressWrapper.eq("if_del", SysEnum.NO.getCode());
        	yestaeUserAddressWrapper.eq("default_address", DefaultAddressEnum.YES.getCode() + "");
        	yestaeUserAddressWrapper.eq("user_id", yestaeUser.getUserId());
        	yestaeUserAddress = yestaeUserAddressService.selectOne(yestaeUserAddressWrapper);

        	EntityWrapper<YestaeUserImage> yestaeUserImageWrapper = new EntityWrapper<YestaeUserImage>();
        	yestaeUserImageWrapper.eq("user_id", yestaeUser.getUserId());
        	yestaeUserImageWrapper.orderBy("create_time", false);
        	YestaeUserImage yestaeUserImage = yestaeUserImageService.selectOne(yestaeUserImageWrapper);
        	if(yestaeUserImage != null){
        		yestaeUserVo.setAvatar(yestaeUserImage.getLarge());
        	}
        }
        model.addAttribute("imgPath", ucConstant.getImageServer());
        model.addAttribute("yestaeUser",yestaeUserVo);
        model.addAttribute("yestaeUserAddress",yestaeUserAddress);
        LogObjectHolder.me().set(yestaeUser);
        return PREFIX + "yestaeUser_edit.html";
    }

    /**
     * 获取用户列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String userId, @RequestParam(required = false) String name
    		, @RequestParam(required = false) String mobile
    		, @RequestParam(required = false) String type, @RequestParam(required = false) String source
    		, @RequestParam(required = false) Long startUpgradeTime, @RequestParam(required = false) Long endUpgradeTime
    		, @RequestParam(required = false) Long startTime, @RequestParam(required = false) Long endTime) {

    	Page<YestaeUser> page = new PageFactory<YestaeUser>().defaultPage();

    	EntityWrapper<YestaeUser> wrapper = new EntityWrapper<YestaeUser>();

    	wrapper.setSqlSelect(new String[]{"id", "user_id", "name", "mobile", "status", "source", "gender", "create_time", "birthday", "type", "upgrade_time"});
    	wrapper.eq("if_del", SysEnum.NO.getCode());
    	wrapper.orderBy("create_time", false);
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("name", name);
    	}
    	if(StringUtils.isNotEmpty(mobile)){
    		wrapper.like("mobile", mobile);
    	}
    	if(StringUtils.isNotEmpty(type)){
    		wrapper.eq("type", type);
    	}
    	if(StringUtils.isNotEmpty(userId)){
    		wrapper.eq("user_id", userId);
    	}
    	if(StringUtils.isNotEmpty(source)){
    		wrapper.eq("source", source);
    	}
    	if(startTime != null){
    		wrapper.gt("create_time", startTime);
    	}
    	if(endTime != null){
    		wrapper.lt("create_time", endTime);
    	}
    	if(startUpgradeTime != null){
    		wrapper.gt("upgrade_time", startUpgradeTime);
    	}
    	if(endUpgradeTime != null){
    		wrapper.lt("upgrade_time", endUpgradeTime);
    	}

    	Page<Map<String, Object>> pageMap = yestaeUserService.selectMapsPage(page, wrapper);

        List<Map<String, Object>> list = pageMap.getRecords();
        for (Map<String, Object> map: list) {
        	map.put("status", (Integer) map.get("status"));
        	map.put("userId", MapUtils.getString(map, "user_id"));
        	map.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(map, "mobile")));
        	map.put("statusName", UserStatusEnum.valueOf((Integer) map.get("status")));
        	map.put("source", CacheKit.get(Cache.CONSTANT, "source:" + MapUtils.getObject(map, "source")));
//        	map.put("source", SourceEnum.valueOf((Integer) map.get("source")));
        	map.put("gender", GenderEnum.valueOf((Integer) map.get("gender")));
        	map.put("type", UserTypeEnum.valueOf((Integer) map.get("type")));
        	map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "create_time")));
        	map.put("upgradeTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "upgrade_time")));
        	map.put("birthday", DateUtils.getTimeString(MapUtils.getLong(map, "birthday")));
        	EntityWrapper<YestaeUserAddress> yestaeUserAddressWrapper = new EntityWrapper<YestaeUserAddress>();
        	yestaeUserAddressWrapper.setSqlSelect(new String[] {"consignee_privince", "consignee_city", "consignee_area"});
        	yestaeUserAddressWrapper.eq("if_del", SysEnum.NO.getCode());
        	yestaeUserAddressWrapper.eq("default_address", DefaultAddressEnum.YES.getCode() + "");
        	yestaeUserAddressWrapper.eq("user_id", MapUtils.getString(map, "user_id"));
        	YestaeUserAddress yestaeUserAddress = yestaeUserAddressService.selectOne(yestaeUserAddressWrapper);
        	if(yestaeUserAddress == null) {
        		continue;
        	}
        	String split = "-";
        	StringBuffer address = new StringBuffer("");
        	if(StringUtils.isNotEmpty(yestaeUserAddress.getConsigneePrivince())){
        		address.append(split);
        		address.append(yestaeUserAddress.getConsigneePrivince());
        	}
        	if(StringUtils.isNotEmpty(yestaeUserAddress.getConsigneeCity())){
        		address.append(split);
        		address.append(yestaeUserAddress.getConsigneeCity());
        	}
        	if(StringUtils.isNotEmpty(yestaeUserAddress.getConsigneeArea())){
        		address.append(split);
        		address.append(yestaeUserAddress.getConsigneeArea());
        	}
        	map.put("address", address.toString().substring(1));
        	map.put("consigneeFullAddress", yestaeUserAddress.getConsigneeFullAddress());
        }
        return super.packForBT(pageMap);
    }

    /**
     * 删除用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除用户", json = "true")
    @ResponseBody
    public Object delete(@RequestParam(required = true) String yestaeUserId) {
    	YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserId);
    	yestaeUser.setUpdateTime(new Date().getTime());
    	yestaeUser.setIfDel(SysEnum.YES.getCode());
        yestaeUserService.updateById(yestaeUser);
        return SUCCESS_TIP;
    }

    /**
     * 启用
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/online")
    @BussinessLog(value = "启用用户", json = "true")
    @ResponseBody
    public Object online(@RequestParam(required = true) String yestaeUserId) {
    	YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserId);
    	yestaeUser.setUpdateTime(new Date().getTime());
    	yestaeUser.setStatus(UserStatusEnum.STATUS_ON.getCode());
    	yestaeUserService.updateById(yestaeUser);
    	return SUCCESS_TIP;
    }

    /**
     * 停用
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/offline")
    @BussinessLog(value = "停用用户", json = "true")
    @ResponseBody
    public Object offline(@RequestParam(required = true) String yestaeUserId) {
    	YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserId);
    	yestaeUser.setUpdateTime(new Date().getTime());
    	yestaeUser.setStatus(UserStatusEnum.STATUS_OFF.getCode());
    	yestaeUserService.updateById(yestaeUser);
    	return SUCCESS_TIP;
    }

    /**
     * 发送支付验证码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/send/code")
    @BussinessLog(value = "发送支付验证码", json = "true")
    @ResponseBody
    public Object sendCode(@RequestParam(required = true) String yestaeUserId,
    		@RequestParam(required = true) Integer type) {
    	YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserId);
    	Map<String, Object> map = new HashMap<>();
    	map.put("code", "200");
    	map.put("message", "操作成功！");
    	map.put("verifyCode", "");

    	/*SmsResult<Boolean> result = smsDubboService.sendCode(yestaeUser.getMobile(), type, SmsTypeEnum.DEPOSIT_PAY.getValue(), new Integer(300));
    	if(result.isSucceed() && result.getResult()){
    		map.put("verifyCode", result.getRetMsg());
    	}else{
    		map.put("message", result.getRetMsg());
    		map.put("code", "0");
    	}*/
    	/*
		短信平台切换
		 */
		ValidCodeDto validCodeDto = new ValidCodeDto();
		validCodeDto.setBizType(SmsTypeEnum.DEPOSIT_PAY.getValue());
		validCodeDto.setMobile(yestaeUser.getMobile());
		validCodeDto.setPlatform(httpSmsConfig.getPlatform());
		String code = String.valueOf(WebUtil.buildRandom(6));
		validCodeDto.setCode(code);
		validCodeDto.setType(0);
		Integer expires = 300;
		validCodeDto.setExpires(expires);
		validCodeDto.setContent(String.format("【益友会】您的大益卡支付验证码是%s，请勿转发、泄露，本验证码%s分钟内有效。",code,expires/60));
		String jsonString = JSONObject.toJSONString(validCodeDto);
		String response = HttpClientUtils.sendPost(httpSmsConfig.getValidapiUrl(), jsonString, Consts.UTF_8);
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		if (jsonObject.get("msg").getAsString().equals("success")){
			JSONObject smsOpenResultDto = JSONObject.parseObject(String.valueOf(jsonObject.get("smsOpenResultDto")));
			if (String.valueOf(smsOpenResultDto.get("succeed")).equals("true")){
				if(!String.valueOf(smsOpenResultDto.get("retCode")).equals("sms.message.send.success")){
					map.put("message", false);
					map.put("code", "0");
				}else {
					map.put("verifyCode", code);
				}
			}
		}else{
			map.put("message", false);
			map.put("code", "0");
		}
    	return map;
    }

    /**
     * 重置密码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/reset/password")
    @BussinessLog(value = "重置密码", json = "true")
    @ResponseBody
    public Object resetPassword(@RequestParam(required = true) String yestaeUserId) {
    	YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserId);


    	UserResult<Boolean> resultUc = userCenterService.findUserPassword(yestaeUser.getMobile(), ucConstant.getDefaultPassword(), null);

    	if(resultUc.isSucceed() && resultUc.getResult()){

    		String messageInfo = "您的益友会账号密码已重置为：" + ucConstant.getDefaultPassword() + "，请尽快登录验证，并设置新密码。";	//发送短信内容
    		SmsResult<Boolean> result = smsDubboService.sendMessage("[\""+yestaeUser.getMobile()+"\"]", messageInfo);
    		if(!result.isSucceed() || !result.getResult()){
    			throw new BussinessException(BizExceptionEnum.ERROR_SEND_SMS_SUCCESS_RESERT_PASSWORD);
    		}
    	}else{
    		return new ErrorTip(0, resultUc.getRetMsg());
    	}
    	return SUCCESS_TIP;
    }

    /**
     * 修改用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改用户", json = "true")
    @ResponseBody
    public Object update(YestaeUserVo yestaeUserVo) {

		YestaeUser yestaeUser = yestaeUserService.selectById(yestaeUserVo.getId());
		if(yestaeUser == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
    	UserResult<Boolean> flag = userCenterService.modifyUserCardNo(yestaeUser.getUserId(), yestaeUserVo.getCardNo());

    	if(!flag.isSucceed() || !flag.getResult()){
    		return new ErrorTip(0, flag.getRetMsg());
    	}

    	return SUCCESS_TIP;
    }

    /**
     * 修改用户手机号
     */
    @RequestMapping(value = "/update/mobile")
    @BussinessLog(value = "修改用户手机号", json = "true")
    @ResponseBody
    public Object updateMobile(YestaeUserVo yestaeUserVo) {

    	//验证手机号是否存在
    	UserResult<Boolean> result = userCenterService.validateIsExistNewMobile(yestaeUserVo.getMobile(), yestaeUserVo.getUserId());

    	if(!result.isSucceed() || result.getResult()){
    		String msg = result.getRetMsg();
    		if(result.getResult()){
    			msg = BizExceptionEnum.MOBILE_EXISTED.getMessage();
    		}
    		return new ErrorTip(0, msg);
    	}

    	//修改手机号
    	UserResult<Boolean> flag = userCenterService.modifyUserMobile(yestaeUserVo.getUserId(),
    			((ShiroUser)getSession().getAttribute("shiroUser")).getId(),
    			yestaeUserVo.getMobile(), yestaeUserVo.getRemark(), null);

    	if(!flag.isSucceed() || !flag.getResult()){
    		return new ErrorTip(0, flag.getRetMsg());
    	}else {
    		//修改实名手机号
			UserRealNameCertificationDubbo userRealNameCertificationDubbo = new UserRealNameCertificationDubbo();
			userRealNameCertificationDubbo.setUserId(Long.valueOf(yestaeUserVo.getUserId()));
			userRealNameCertificationDubbo.setMobile(yestaeUserVo.getMobile());
			UserResult<Boolean> userRealNameMobileResult =
					userCenterRealNameAuthenticationService.updataUserRealNameMobile(userRealNameCertificationDubbo);

			if(!userRealNameMobileResult.isSucceed() || !userRealNameMobileResult.getResult()){
				return new ErrorTip(Integer.parseInt(userRealNameMobileResult.getRetCode()), userRealNameMobileResult.getRetMsg());
			}
		}
    	return SUCCESS_TIP;
    }

	/**
	 * 强制修改用户手机号
	 */
	@RequestMapping(value = "/update/mobile/forcedModification")
	@BussinessLog(value = "修改用户手机号", json = "true")
	@ResponseBody
	public Object updateMobileBak(YestaeUserVo yestaeUserVo) {

		//验证手机号是否存在
		UserResult<Boolean> result = userCenterService.validateIsExistNewMobile(yestaeUserVo.getMobile(), yestaeUserVo.getUserId());

		if(!result.isSucceed() || result.getResult()){
			String msg = result.getRetMsg();
			if(result.getResult()){
				msg = BizExceptionEnum.MOBILE_EXISTED.getMessage();
			}
			return new ErrorTip(0, msg);
		}

		//修改手机号
		UserResult<Boolean> flag = userCenterService.modifyUserMobile(yestaeUserVo.getUserId(),
				((ShiroUser)getSession().getAttribute("shiroUser")).getId(),
				yestaeUserVo.getMobile(), yestaeUserVo.getRemark()+"ForcedModification", null);

		if(!flag.isSucceed() || !flag.getResult()){
			return new ErrorTip(0, flag.getRetMsg());
		}else {
			//修改实名手机号
			UserRealNameCertificationDubbo userRealNameCertificationDubbo = new UserRealNameCertificationDubbo();
			userRealNameCertificationDubbo.setUserId(Long.valueOf(yestaeUserVo.getUserId()));
			userRealNameCertificationDubbo.setMobile(yestaeUserVo.getMobile());
			UserResult<Boolean> userRealNameMobileResult =
					userCenterRealNameAuthenticationService.updataUserRealNameMobile(userRealNameCertificationDubbo);

			if(!userRealNameMobileResult.isSucceed() || !userRealNameMobileResult.getResult()){
				return new ErrorTip(Integer.parseInt(userRealNameMobileResult.getRetCode()), userRealNameMobileResult.getRetMsg());
			}
		}
		return SUCCESS_TIP;
	}

    /**
     * 根据手机号获取用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/user/mobile")
    @ResponseBody
    public Object detail(@RequestParam(required = true) String mobile) {
    	Wrapper<YestaeUser> wrapper = new EntityWrapper<YestaeUser>();
    	wrapper.setSqlSelect(new String[]{"id", "user_id", "name", "mobile", "status", "source", "gender", "create_time", "birthday", "type", "upgrade_time"});
    	wrapper.eq("if_del", SysEnum.NO.getCode());
    	if(StringUtils.isNotEmpty(mobile)){
    		wrapper.eq("mobile", mobile);
    		return yestaeUserService.selectOne(wrapper);
    	}
    	return "";
    }

    /**
     * 用户详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeUserId}")
    @ResponseBody
    public Object getUserByMobile(@PathVariable("yestaeUserId") String yestaeUserId) {
        return yestaeUserService.selectById(yestaeUserId);
    }
}
