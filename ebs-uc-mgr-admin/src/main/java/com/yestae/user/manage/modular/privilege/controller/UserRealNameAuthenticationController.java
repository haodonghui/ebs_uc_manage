package com.yestae.user.manage.modular.privilege.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.yestae.user.center.dubbo.entity.UserRealNameAuthenticationDubbo;
import com.yestae.user.center.dubbo.entity.UserResult;
import com.yestae.user.center.dubbo.entity.UserVerifyParameter;
import com.yestae.user.center.dubbo.service.IUserCenterRealNameAuthenticationService;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.common.util.Convert;
import com.yestae.user.manage.common.annotion.BussinessLog;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.base.tips.ErrorTip;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroKit;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.ImageUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.BornEnum;
import com.yestae.user.manage.modular.privilege.common.enums.GenderEnum;
import com.yestae.user.manage.modular.privilege.common.enums.IdNoEnum;
import com.yestae.user.manage.modular.privilege.common.enums.UserRealNameAuthenticationFlag;
import com.yestae.user.manage.modular.privilege.common.enums.UserTypeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.VerifyEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.UserRealNameAuthentication;
import com.yestae.user.manage.modular.privilege.service.IUserRealNameAuthenticationService;
import com.yestae.user.manage.utils.CheckUtil;

/**
 * 实名认证控制器
 *
 * @author fengshuonan
 * @Date 2018-03-23 09:59:44
 */
@Controller
@RequestMapping("/userRealNameAuthentication")
public class UserRealNameAuthenticationController extends BaseController {

    private String PREFIX = "/privilege/userRealNameAuthentication/";
    
    @Resource
	UcConstant ucConstant;
    
    @Resource
    private ImageUtil imageUtil;

    @Autowired
    private IUserRealNameAuthenticationService userRealNameAuthenticationService;
    
    @DubboReference
	private IUserCenterRealNameAuthenticationService userCenterRealNameAuthenticationService;
    
    //日志
    private static Logger log = LoggerFactory.getLogger("AUTH_MSG_LOG");
    

    /**
     * 跳转到实名认证首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userRealNameAuthentication.html";
    }

    /**
     * 跳转到添加实名认证
     */
    @RequestMapping("/userRealNameAuthentication_add")
    public String userRealNameAuthenticationAdd() {
        return PREFIX + "userRealNameAuthentication_add.html";
    }

    /**
     * 跳转到修改实名认证
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/userRealNameAuthentication_update/{userRealNameAuthenticationId}")
    public String userRealNameAuthenticationUpdate(@PathVariable String userRealNameAuthenticationId, Model model) {
        UserRealNameAuthentication userRealNameAuthentication = userRealNameAuthenticationService.getById(userRealNameAuthenticationId);
        
        if(userRealNameAuthentication == null){
    		throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
    	}
        
        //是否重复标识，"0"-不重复, "1"-重复
        model.addAttribute("id1Flag", "0");
        model.addAttribute("id2Flag", "0");
        
        if(!StringUtils.isEmpty(userRealNameAuthentication.getId1No()) 
        		&& !StringUtils.isEmpty(userRealNameAuthentication.getId1Type())){
        	
        	int i = userRealNameAuthenticationService.selectCountByIdNo(userRealNameAuthentication.getId1No()
        			, userRealNameAuthentication.getId1Type());
        	if(i > 1){
        		model.addAttribute("id1Flag", "1");
        	}
        }
        if(!StringUtils.isEmpty(userRealNameAuthentication.getId2No())
        		&& !StringUtils.isEmpty(userRealNameAuthentication.getId2Type())){
        	
        	int i = userRealNameAuthenticationService.selectCountByIdNo(userRealNameAuthentication.getId2No()
        			, userRealNameAuthentication.getId2Type());
        	if(i > 1){
        		model.addAttribute("id2Flag", "1");
        	}
        }
        
//        model.addAttribute("userRealNameAuthentication",userRealNameAuthentication);
//        model.addAttribute("born", userRealNameAuthentication.getBorn());
//        model.addAttribute("gender", GenderEnum.valueOf(Convert.toInt(userRealNameAuthentication.getGender())));
//        model.addAttribute("createTime", DateUtils.toDatetimeString(userRealNameAuthentication.getCreateTime()));
//        model.addAttribute("id1Type", IdNoEnum.getMessage(userRealNameAuthentication.getId1Type()));
//        model.addAttribute("id2Type", IdNoEnum.getMessage(userRealNameAuthentication.getId2Type()));
//        model.addAttribute("dataSource", CacheKit.get(Cache.CONSTANT, "realNameAuthDataSource:" + userRealNameAuthentication.getDataSource()));
        model.addAttribute("userRealNameAuthentication",userRealNameAuthentication);
        model.addAttribute("born", userRealNameAuthentication.getBorn());
        model.addAttribute("gender", userRealNameAuthentication.getGender());
        model.addAttribute("createTime", DateUtils.toDatetimeString(userRealNameAuthentication.getCreateTime()));
        model.addAttribute("id1Type", userRealNameAuthentication.getId1Type());
        model.addAttribute("id2Type", userRealNameAuthentication.getId2Type());
        model.addAttribute("dataSource", userRealNameAuthentication.getDataSource());
        model.addAttribute("imgPath", ucConstant.getImageServer());
//        LogObjectHolder.me().set(userRealNameAuthentication);
        return PREFIX + "userRealNameAuthentication_edit2.html";
    }

    /**
     * 获取实名认证列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        
    	Page<Map<String, Object>> page = new Page();
    	
    	Map<String, String> paramMap = HttpKit.getRequestParameters();
        
        List<Map<String, Object>> list = userRealNameAuthenticationService.selectUserRealNameAuthenticationList(page, paramMap);
        for (Map<String, Object> map: list) {
        	map.put("userId", MapUtils.getString(map, "userId"));
        	map.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(map, "mobile")));
        	map.put("loginMobile", PrivacyHideUtil.mobileHide(MapUtils.getString(map, "loginMobile")));
        	map.put("id1Type", IdNoEnum.getMessage(MapUtils.getString(map, "id1Type")));
        	map.put("id2Type", IdNoEnum.getMessage(MapUtils.getString(map, "id2Type")));
        	map.put("id1No", encryptString(MapUtils.getString(map, "id1No")));
        	map.put("id2No", encryptString(MapUtils.getString(map, "id2No")));
        	map.put("bankCardNo", MapUtils.getString(map, "bankCardNo"));
        	map.put("dataSource", CacheKit.get(Cache.CONSTANT, "realNameAuthDataSource:" + MapUtils.getString(map, "dataSource")));
        	map.put("verifyChinese", VerifyEnum.getMessage(MapUtils.getString(map, "verify")));
        	map.put("flag", UserRealNameAuthenticationFlag.getMessage(MapUtils.getString(map, "flag")));
        	map.put("born", BornEnum.getMessage(MapUtils.getString(map, "born")));
        	map.put("type", UserTypeEnum.valueOf(MapUtils.getInteger(map, "type")));
        	map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
        	map.put("verifyTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "verifyTime")));
        	map.put("registTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "registTime")));
        }
        page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 获取实名认证列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/listV1")
    @ResponseBody
    public Object listV1(@RequestParam(required = false) String idNo, @RequestParam(required = false) String bankCardNo, 
    		@RequestParam(required = false) String name, @RequestParam(required = false) String mobile
    		, @RequestParam(required = false) String flag, @RequestParam(required = false) String realName
    		, @RequestParam(required = false) String verify, @RequestParam(required = false) String verifyName
    		, @RequestParam(required = false) Long startTime, @RequestParam(required = false) Long endTime) {

		Page<Map<String, Object>> page = new Page<>();
    	
    	QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
    	
    	/*wrapper.setSqlSelect(new String[]{"id", "name", "real_name", "mobile", "verify", "born", "flag", "create_time",
    			"verify_name", "verify_time", "id1_type", "id1_no", "id2_type", "id2_no", "bank_card_no", "data_source"});*/
    	wrapper.orderBy(true ,true,"verify");
    	wrapper.orderBy(true ,true,"create_time");
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("name", name);
    	}
    	if(StringUtils.isNotEmpty(realName)){
    		wrapper.like("real_name", realName);
    	}
    	if(StringUtils.isNotEmpty(bankCardNo)){
    		wrapper.like("bank_card_no", bankCardNo);
    	}
    	if(StringUtils.isNotEmpty(verifyName)){
    		wrapper.like("verify_name", verifyName);
    	}
    	if(StringUtils.isNotEmpty(mobile)){
    		wrapper.like("mobile", mobile);
    	}
    	if(StringUtils.isNotEmpty(idNo)){
    		wrapper.like("id1_no", idNo)
    		.or().like("id2_no", idNo)
    		.like("id3_no", idNo);
    	}
    	if(StringUtils.isNotEmpty(verify)){
    		wrapper.eq("verify", verify);
    	}
    	if(StringUtils.isNotEmpty(flag)){
    		wrapper.eq("flag", flag);
    	}
    	if(startTime != null){
    		wrapper.gt("create_time", startTime);
    	}
    	if(endTime != null){
    		wrapper.lt("create_time", endTime);
    	}
    	
    	Page<Map<String, Object>> pageMap = userRealNameAuthenticationService.pageMaps(page, wrapper);
    	
    	List<Map<String, Object>> list = pageMap.getRecords();
    	for (Map<String, Object> map: list) {
    		map.put("verifyName", MapUtils.getString(map, "verify_name"));
    		map.put("realName", MapUtils.getString(map, "real_name"));
    		map.put("id1Type", IdNoEnum.getMessage(MapUtils.getString(map, "id1_type")));
    		map.put("id2Type", IdNoEnum.getMessage(MapUtils.getString(map, "id2_type")));
    		map.put("id1No", MapUtils.getString(map, "id1_no"));
    		map.put("id2No", MapUtils.getString(map, "id2_no"));
    		map.put("bankCardNo", MapUtils.getString(map, "bank_card_no"));
    		map.put("dataSource", CacheKit.get(Cache.CONSTANT, "realNameAuthDataSource:" + MapUtils.getString(map, "data_source")));
    		map.put("verifyChinese", VerifyEnum.getMessage(MapUtils.getString(map, "verify")));
    		map.put("flag", UserRealNameAuthenticationFlag.getMessage(MapUtils.getString(map, "flag")));
    		map.put("born", BornEnum.getMessage(MapUtils.getString(map, "born")));
    		map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "create_time")));
    		map.put("verifyTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "verify_time")));
    	}
    	
    	return super.packForBT(pageMap);
    }
    
    /**
     * 新增实名认证
     */
    @DataSource(name="dataSourceUc")
    @BussinessLog(value = "新增实名认证", json = "true")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserRealNameAuthenticationDubbo userRealNameAuthenticationDubbo) {
    	//相关信息校验
    	//真实姓名校验,只能输入中文
    	if(!CheckUtil.isChineseWord(userRealNameAuthenticationDubbo.getRealName())){
    		return new ErrorTip(0, "真实姓名请输入中文");
    	}
    	//身份校验
    	if(Objects.equal("3", userRealNameAuthenticationDubbo.getId1Type())){
    		String id1No = userRealNameAuthenticationDubbo.getId1No();
    		boolean falg = CheckUtil.checkIdNo(id1No);
    		if(!falg){
    			return new ErrorTip(0, "身份证号不正确，请确认重新输入");
    		}
    		//校验生肖
    		if(StringUtils.isBlank(userRealNameAuthenticationDubbo.getBorn()) || !CheckUtil.checkZodiac(id1No, Integer.valueOf(userRealNameAuthenticationDubbo.getBorn()))){
    			Joiner joiner = Joiner.on(",").skipNulls(); 
    			String join = joiner.join(CheckUtil.getZodiac(id1No));
    			return new ErrorTip(0, "生肖不正确，系统计算生肖为["+join+"](如果为两个选则其中一个即可)");
    		}
    		
    		//校验身份证是否被其他用户占用
    		QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
        	wrapper.eq("id1_no", id1No);
        	List<UserRealNameAuthentication> id1NoList = userRealNameAuthenticationService.list(wrapper);
        	if(!CollectionUtils.isEmpty(id1NoList)){
        		UserRealNameAuthentication realNameAuthentication = id1NoList.get(0);
        		return new ErrorTip(0, "该身份证已经被["+realNameAuthentication.getRealName()+"] 实名过");
        	}
    		
    	}
    	if(Objects.equal("3", userRealNameAuthenticationDubbo.getId2Type())){
    		String id2No = userRealNameAuthenticationDubbo.getId2No();
    		boolean falg = CheckUtil.checkIdNo(id2No);
    		if(!falg){ 
    			return new ErrorTip(0, "身份证号不正确，请确认重新输入");
    		}
    		//校验生肖
    		if(StringUtils.isBlank(userRealNameAuthenticationDubbo.getBorn()) || !CheckUtil.checkZodiac(id2No, Integer.valueOf(userRealNameAuthenticationDubbo.getBorn()))){
    			Joiner joiner = Joiner.on(",").skipNulls(); 
    			String join = joiner.join(CheckUtil.getZodiac(id2No));
    			return new ErrorTip(0, "生肖不正确，系统计算生肖为["+join+"](如果为两个选则其中一个即可)");
    		}
    		
    		//校验身份证是否被其他用户占用
    		QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
        	wrapper.eq("id2_no", id2No);
        	List<UserRealNameAuthentication> id2NoList = userRealNameAuthenticationService.list(wrapper);
        	if(!CollectionUtils.isEmpty(id2NoList)){
        		UserRealNameAuthentication realNameAuthentication = id2NoList.get(0);
        		return new ErrorTip(0, "该身份证已经被["+realNameAuthentication.getRealName()+"] 实名过");
        	}
    	}
    	
    	//银行卡校验
    	if(!CheckUtil.numberCheck(userRealNameAuthenticationDubbo.getBankCardNo(), 5, 25)){
    		return new ErrorTip(0, "银行卡只能填写5到25位数字");
    	}
    	
    	//校验当前手机号和身份证是否被其他用户绑定
    	QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
    	wrapper.eq("mobile", userRealNameAuthenticationDubbo.getMobile());
    	List<UserRealNameAuthentication> mobileList = userRealNameAuthenticationService.list(wrapper);
    	
    	if(!CollectionUtils.isEmpty(mobileList)){
    		UserRealNameAuthentication realNameAuthentication = mobileList.get(0);
    		return new ErrorTip(0, "该手机号已经被["+realNameAuthentication.getRealName()+"] 实名过");
    	}
    	
    	
    	userRealNameAuthenticationDubbo.setVerify(VerifyEnum.PASS.getCode());
		/*add 2019年10月18日*/
		userRealNameAuthenticationDubbo.setVerify_name("系统");
		userRealNameAuthenticationDubbo.setVerify_time(new Date().getTime());
		/*add 2019年10月18日*/
    	UserResult<Boolean> result = userCenterRealNameAuthenticationService.saveAuthenticationInformation(userRealNameAuthenticationDubbo);
    	if(!result.isSucceed() || !result.getResult()){
			return new ErrorTip(0, result.getRetMsg());
		}
    	return SUCCESS_TIP;
    }
    
    /**
     * 编辑实名认证
     */
    @DataSource(name="dataSourceUc")
    @BussinessLog(value = "编辑实名认证", json = "true")
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(UserRealNameAuthentication userRealNameAuthentication) {
    	//通过id查询出原始数据，记录日志,TODO 后期记录到表中
    	String id = userRealNameAuthentication.getId();
    	UserRealNameAuthentication oldRealNameAuthentication = userRealNameAuthenticationService.getById(id);
    	log.info("method#[{}], userRealNameAuthentication.id#[{}], oldRealNameAuthentication#[{}]",
    				"UserRealNameAuthenticationController.edit", id, JSON.toJSONString(oldRealNameAuthentication));
    	//相关信息校验
    	//真实姓名校验,只能输入中文
    	if(!CheckUtil.isChineseWord(userRealNameAuthentication.getRealName())){
    		return new ErrorTip(0, "真实姓名请输入中文");
    	}
    	//身份校验
    	if(Objects.equal("3", userRealNameAuthentication.getId1Type())){
    		String id1No = userRealNameAuthentication.getId1No();
    		boolean falg = CheckUtil.checkIdNo(id1No);
    		if(!falg){
    			return new ErrorTip(0, "身份证号不正确，请确认重新输入");
    		}
    		//校验生肖
    		if(StringUtils.isBlank(userRealNameAuthentication.getBorn()) || !CheckUtil.checkZodiac(id1No, Integer.valueOf(userRealNameAuthentication.getBorn()))){
    			Joiner joiner = Joiner.on(",").skipNulls(); 
    			String join = joiner.join(CheckUtil.getZodiac(id1No));
    			return new ErrorTip(0, "生肖不正确，系统计算生肖为["+join+"](如果为两个选则其中一个即可)");
    		}
    		
    		//校验身份证是否被其他用户占用
    		QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
        	wrapper.eq("id1_no", id1No).notIn("id", id);
        	List<UserRealNameAuthentication> id1NoList = userRealNameAuthenticationService.list(wrapper);
        	if(!CollectionUtils.isEmpty(id1NoList)){
        		UserRealNameAuthentication realNameAuthentication = id1NoList.get(0);
        		return new ErrorTip(0, "该身份证已经被 ["+realNameAuthentication.getRealName()+"] 实名过");
        	}
    		
    	}
    	if(Objects.equal("3", userRealNameAuthentication.getId2Type())){
    		String id2No = userRealNameAuthentication.getId2No();
    		boolean falg = CheckUtil.checkIdNo(id2No);
    		if(!falg){ 
    			return new ErrorTip(0, "身份证号不正确，请确认重新输入");
    		}
    		//校验生肖
    		if(StringUtils.isBlank(userRealNameAuthentication.getBorn()) || !CheckUtil.checkZodiac(id2No, Integer.valueOf(userRealNameAuthentication.getBorn()))){
    			Joiner joiner = Joiner.on(",").skipNulls(); 
    			String join = joiner.join(CheckUtil.getZodiac(id2No));
    			return new ErrorTip(0, "生肖不正确，系统计算生肖为["+join+"](如果为两个选则其中一个即可)");
    		}
    		
    		//校验身份证是否被其他用户占用
    		QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
        	wrapper.eq("id2_no", id2No).notIn("id", id);
        	List<UserRealNameAuthentication> id2NoList = userRealNameAuthenticationService.list(wrapper);
        	if(!CollectionUtils.isEmpty(id2NoList)){
        		UserRealNameAuthentication realNameAuthentication = id2NoList.get(0);
        		return new ErrorTip(0, "该身份证已经被[ "+realNameAuthentication.getRealName()+"] 实名过");
        	}
    	}
    	
    	//银行卡校验
    	if(!CheckUtil.numberCheck(userRealNameAuthentication.getBankCardNo(), 5, 25)){
    		return new ErrorTip(0, "银行卡只能填写5到25位数字");
    	}
    	
    	QueryWrapper<UserRealNameAuthentication> wrapper = new QueryWrapper<UserRealNameAuthentication>();
    	//手机号相同，实名id不相同
    	wrapper.eq("mobile", userRealNameAuthentication.getMobile()).notIn("id", id);
    	List<UserRealNameAuthentication> mobileList = userRealNameAuthenticationService.list(wrapper);
    	
    	if(!CollectionUtils.isEmpty(mobileList)){
    		UserRealNameAuthentication realNameAuthentication = mobileList.get(0);
    		return new ErrorTip(0, "该手机号已经被[ "+realNameAuthentication.getRealName()+"] 实名过");
    	}
    	userRealNameAuthenticationService.updateById(userRealNameAuthentication);
    	return SUCCESS_TIP;
    }
    
    /**
     * 修改实名认证次数
     */
    @DataSource(name="dataSourceUc")
    @BussinessLog(value = "修改实名认证次数", json = "true")
    @RequestMapping(value = "/update/commitCount")
    @ResponseBody
    public Object updateCommitCount(@RequestParam(required = true) Integer commitCount) {
    	UserResult<Boolean> result = userCenterRealNameAuthenticationService.modifyCertificationMaxCommitCount(commitCount);
    	if(!result.isSucceed() || !result.getResult()){
    		return new ErrorTip(0, result.getRetMsg());
    	}
    	return SUCCESS_TIP;
    }
    
    /**
     * 查询实名认证次数
     */
    @DataSource(name="dataSourceUc")
    @BussinessLog(value = "查询实名认证次数", json = "true")
    @RequestMapping(value = "/find/commitCount")
    @ResponseBody
    public Object findCommitCount() {
    	UserResult<Integer> result = userCenterRealNameAuthenticationService.findCertificationMaxCommitCount();
    	Map<String, Object> map = new HashMap<>();
    	if(!result.isSucceed()){
    		return new ErrorTip(0, result.getRetMsg());
    	}
    	map.put("commitCount", result.getResult());
    	return map;
    }

    /**
     * 删除实名认证
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String userRealNameAuthenticationId) {
        userRealNameAuthenticationService.removeById(userRealNameAuthenticationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改实名认证
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserRealNameAuthentication userRealNameAuthentication) {
        userRealNameAuthenticationService.updateById(userRealNameAuthentication);
        return SUCCESS_TIP;
    }
    
    /**
     * 审核实名认证
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/verify")
    @BussinessLog(value = "审核实名认证", json = "true")
    @ResponseBody
    public Object verify(@RequestParam(required = true) String userRealNameAuthenticationId,
    		@RequestParam(required = true) String verify, @RequestParam(required = false) String verifyDesc) {
    	
    	UserRealNameAuthentication userRealNameAuthentication = userRealNameAuthenticationService.getById(userRealNameAuthenticationId);
    	
    	if(userRealNameAuthentication == null){
    		throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
    	}
    	
    	UserVerifyParameter userVerifyParameter = new UserVerifyParameter();
    	userVerifyParameter.setId(userRealNameAuthenticationId);
    	userVerifyParameter.setUserId(Convert.toStr(userRealNameAuthentication.getUserId()));
    	userVerifyParameter.setVerify(verify);
    	userVerifyParameter.setVerifyDesc(verifyDesc);
    	userVerifyParameter.setVerifyId(ShiroKit.getUser().getId());
    	userVerifyParameter.setVerifyName(ShiroKit.getUser().getName());
    	userVerifyParameter.setVerifyTime(new Date().getTime());
		UserResult<Boolean> flag = userCenterRealNameAuthenticationService
    			.modifyAuthenticationInformation(userVerifyParameter);
		if(!flag.isSucceed() || !flag.getResult()){
			return new ErrorTip(0, flag.getRetMsg());
		}
    	return SUCCESS_TIP;
    }
    
    /**
     * 实名认证置为失效
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/disabled")
    @BussinessLog(value = "实名认证置为失效", json = "true")
    @ResponseBody
    public Object disabled(@RequestParam(required = true) String userRealNameAuthenticationId) {
    	
    	UserRealNameAuthentication userRealNameAuthentication = userRealNameAuthenticationService.getById(userRealNameAuthenticationId);
    	
    	if(userRealNameAuthentication == null){
    		throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
    	}
    	
    	UserResult<Boolean> result = userCenterRealNameAuthenticationService
    			.modifyAuthenticationInformation(userRealNameAuthenticationId, Convert.toStr(userRealNameAuthentication.getUserId()), UserRealNameAuthenticationFlag.FALSE.getCode());
		if(!result.isSucceed() || !result.getResult()){
			return new ErrorTip(0, result.getRetMsg());
		}
    	return SUCCESS_TIP;
    }

    /**
     * 实名认证详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{userRealNameAuthenticationId}")
    public Object detail(@PathVariable("userRealNameAuthenticationId") String userRealNameAuthenticationId, Model model) {
    	UserRealNameAuthentication userRealNameAuthentication = userRealNameAuthenticationService.getById(userRealNameAuthenticationId);
    	if(userRealNameAuthentication == null){
    		throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
    	}
    	
    	//是否重复标识，"0"-不重复, "1"-重复
        model.addAttribute("id1Flag", "0");
        model.addAttribute("id2Flag", "0");
        
        if(!StringUtils.isEmpty(userRealNameAuthentication.getId1No()) 
        		&& !StringUtils.isEmpty(userRealNameAuthentication.getId1Type())){
        	
        	int i = userRealNameAuthenticationService.selectCountByIdNo(userRealNameAuthentication.getId1No()
        			, userRealNameAuthentication.getId1Type());
        	if(i > 1){
        		model.addAttribute("id1Flag", "1");
        	}
        }
        if(!StringUtils.isEmpty(userRealNameAuthentication.getId2No())
        		&& !StringUtils.isEmpty(userRealNameAuthentication.getId2Type())){
        	
        	int i = userRealNameAuthenticationService.selectCountByIdNo(userRealNameAuthentication.getId2No()
        			, userRealNameAuthentication.getId2Type());
        	if(i > 1){
        		model.addAttribute("id2Flag", "1");
        	}
        }
    	
    	model.addAttribute("userRealNameAuthentication",userRealNameAuthentication);
        model.addAttribute("born", BornEnum.getMessage(userRealNameAuthentication.getBorn()));
        model.addAttribute("gender", GenderEnum.valueOf(Convert.toInt(userRealNameAuthentication.getGender())));
        model.addAttribute("createTime", DateUtils.toDatetimeString(userRealNameAuthentication.getCreateTime()));
        model.addAttribute("id1Type", IdNoEnum.getMessage(userRealNameAuthentication.getId1Type()));
        model.addAttribute("id2Type", IdNoEnum.getMessage(userRealNameAuthentication.getId2Type()));
        model.addAttribute("verify", VerifyEnum.getMessage(userRealNameAuthentication.getVerify()));
//        LogObjectHolder.me().set(userRealNameAuthentication);
        model.addAttribute("imgPath", ucConstant.getImageServer());
        model.addAttribute("dataSource", CacheKit.get(Cache.CONSTANT, "realNameAuthDataSource:" + userRealNameAuthentication.getDataSource()));
        return PREFIX + "userRealNameAuthentication_detail.html";
    }
    
    /**
     * 上传图片
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {
    	
    	String originalFileName = picture.getOriginalFilename();
    	
    	//校验图片类型
    	String rex = ".*\\.(?i)(jpg|png|bmp|gif)";
    	if(!originalFileName.matches(rex)){
    		throw new BussinessException(BizExceptionEnum.UPLOAD_TYPE_ERROR);
    	}
        try {
        	//图片类型
        	String type = String.valueOf(originalFileName.substring(originalFileName.lastIndexOf(".")));
        	//图片名称
        	String fName = new Date().getTime() + type;
        	//上传原图
        	String path = imageUtil.uploadFile(picture, ucConstant.getImageDir() + ucConstant.getUserRealNameImageDir(), fName);
        	String large = ucConstant.getUserRealNameImageDir() + path + "/" + fName;
        	//创建缩略图 200px * 200px
        	imageUtil.scaleImageWithParams(ucConstant.getImageDir() + large, 
        			ucConstant.getImageDir() + ucConstant.getUserRealNameImageDir() + path + "/thumb", null, 200, 200, true);
        
        	return large;
        } catch (Exception e) {
        	e.printStackTrace();
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
    }
    
    private static String encryptString(String str){
    	if(!StringUtils.isEmpty(str) && str.length() >= 2){
    		char[] c = str.toCharArray();
    		int l = str.length() >= 6? 6: str.length();
    		for(int i = 2; i < l; i++){
    			c[i] = '*';
    		}
    		return String.valueOf(c);
    	}
    	return str;
    }
}
