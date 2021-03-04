package com.yestae.user.manage.modular.vas.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.yestae.user.center.dubbo.entity.*;
import com.yestae.user.center.dubbo.service.IUserCenterAddedService;
import com.yestae.user.center.dubbo.service.IUserCenterService;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.Convert;
import com.yestae.user.common.util.ThreadPoolExecutorUtil;
import com.yestae.user.common.util.ToolUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.DataSourceContextHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.Constants.UserConstant;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUser;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserService;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.model.Record;
import com.yestae.user.manage.modular.vas.service.IRecordService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户增值服务控制器
 *
 * @author fengshuonan
 * @Date 2018-07-25 10:39:28
 */
@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(RecordController.class);
    private String PREFIX = "/vas/record/";

    @Autowired
    private IRecordService recordService;
    @Autowired
    private IYestaeUserService yestaeUserService;
    @Resource
   	private IUserCenterService userCenterService;
    @Resource
    private IUserCenterAddedService userCenterAddedService;
    @Resource
    ExcelUtil excelUtil;
    /**
     * 跳转到用户增值服务首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "record.html";
    }

    /**
     * 跳转到添加用户增值服务
     */
    @RequestMapping("/record_add")
    public String recordAdd() {
        return PREFIX + "record_add.html";
    }
    /**
     * 跳转到添加用户绑定增值服务页
     */
    @RequestMapping("/record_bind")
    public String recordBind() {
    	return PREFIX + "record_bind.html";
    }

    /**
     * 跳转到修改用户增值服务
     */
    @RequestMapping("/record_update/{recordId}")
    @DataSource(name="dataSourceUc")
    public String recordUpdate(@PathVariable String recordId, Model model) {
        Record record = recordService.selectById(recordId);
        model.addAttribute("record",record);
        LogObjectHolder.me().set(record);
        return PREFIX + "record_edit.html";
    }

    /**
     * 获取用户增值服务列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(String condition) {
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>> ().defaultPage();
    	Map<String, Object> paramMap = HttpKit.getRequestParametersMap();
    	String name = MapUtils.getString(paramMap, "name");
    	String mobile = MapUtils.getString(paramMap, "mobile");
    	
    	List<YestaeUser> userList = null;
    	List<Map<String, Object>> recordList = new ArrayList<>();
    	
    	if(!StringUtils.isEmpty(name) || !StringUtils.isEmpty(mobile)){
    		
    		EntityWrapper<YestaeUser> wrapper = new EntityWrapper<YestaeUser>();
    		if(!StringUtils.isEmpty(name)){
    			wrapper.eq("name", name);
    		}
    		if(!StringUtils.isEmpty(mobile)){
    			wrapper.eq("mobile", mobile);
    		}
    		userList = yestaeUserService.selectList(wrapper);
    		if(userList != null && userList.size() > 0){
    			List<Long> userIdList = new ArrayList<>();
        		for(YestaeUser user: userList){ 
        			userIdList.add(new Long(user.getUserId()));
        		}
        		paramMap.put("userIds", userIdList);
        		recordList = recordService.selectRecordList(page, paramMap);
    		}
    	}else{
    		
    		recordList = recordService.selectRecordList(page, paramMap);
    		
    		if(recordList != null && recordList.size() > 0){
    			
    			List<Long> userIdList = new ArrayList<>();
    			for(Map<String, Object> map: recordList){ 
    				Long userId =  MapUtils.getLong(map, "userId");
    				if(userId != null){
    					userIdList.add(userId);
    				}
    			}
    			if(userIdList.size() > 0){
    				
    				EntityWrapper<YestaeUser> wrapper = new EntityWrapper<YestaeUser>();
    				wrapper.in("user_id", userIdList);
    				userList = yestaeUserService.selectList(wrapper);
    			}
    		}
    	}
    	if(recordList != null && recordList.size() > 0
    			&& userList != null && userList.size() > 0){
    		
    		for(Map<String, Object> map: recordList){
    			for(YestaeUser user: userList){
    				String userId =  MapUtils.getString(map, "userId");
    				if(user.getUserId().equals(userId)){
    					map.put("userId", userId);
    					map.put("name", user.getName());
    					map.put("mobile", PrivacyHideUtil.mobileHide(user.getMobile()));
    					map.put("beginTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "beginTime")));
    					map.put("vasCode", MapUtils.getString(map, "vasCode"));
    					map.put("endTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "endTime")));
    					map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    					map.put("updateTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "updateTime")));
    					map.put("stopName", CacheKit.get(Cache.CONSTANT, "recordStop:" + MapUtils.getObject(map, "stop")));//显示状态//("","码表字段","数据库字段")
    					map.put("invalidName", CacheKit.get(Cache.CONSTANT, "recordInvalid:" + MapUtils.getObject(map, "invalid")));//显示是否有效
    					break;
    				}
    			}
    		}
    	}
    	
    	page.setRecords(recordList);
    	
        return super.packForBT(page);
    }

    /**
     * 导入用户并绑定增值服务
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/import")
    @DataSource(name="dataSourceUc")
    @ResponseBody
	public Object importVasRecord(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response){
    	String vasId = request.getParameter("vasId");
    	Map<String, Object> retMap = new HashMap<>();
    	
    	if(StringUtils.isEmpty(vasId)){
    		retMap.put("code", "0");
    		retMap.put("msg", "请选择增值服务");
    		return retMap;
    	}
    	List<Map<String, Object>> warnList = new Vector<>();
    	List<Map<String, Object>> addedOrderPaymentParameterList = new Vector<>();
    	retMap.put("warnList", warnList);
    	InputStream in = null;
		try {
			in = file.getInputStream();
			List<List<String>> list =  excelUtil.getListByExcel(in, "/" + file.getOriginalFilename());
			
			//创建线程池
			ThreadPoolExecutorUtil executorService = new ThreadPoolExecutorUtil(100);
			
			String dataSourceType = DataSourceContextHolder.getDataSourceType();
			
			for(List<String> l: list){
				
				executorService.execute(new Runnable(){
					
					@Override
					public void run() {
						//手机号为空
						if(2 > l.size() || StringUtils.isEmpty(l.get(1))){
							return;
						}
						String name = l.get(0);
						String mobile = l.get(1);
						//手机号不正确
						if(!ToolUtil.checkMobile(mobile)){
							Map<String, Object> warnMap = new HashMap<>();
							warnMap.put("name", name);
							warnMap.put("mobile", mobile);
							warnMap.put("warn", "导入失败，手机号不正确");
							warnList.add(warnMap);
							return;
						}
						
						//多线程需要设置数据源
						DataSourceContextHolder.setDataSourceType(dataSourceType);
						
						Wrapper<YestaeUser> userWrapper = new EntityWrapper<>();
						userWrapper.eq("if_del", SysEnum.NO.getCode());
						userWrapper.eq("mobile", mobile);
						List<YestaeUser> userList = yestaeUserService.selectList(userWrapper);
						
						Long userId = null;
						
						//用户存在
						if(userList != null && userList.size() > 0){
							YestaeUser yestaeUser = userList.get(0);
							userId = Convert.toLong(yestaeUser.getUserId());
							
							Wrapper<Record> recordWrapper = new EntityWrapper<>();
							recordWrapper.eq("added_service_id", vasId);
							recordWrapper.eq("user_id", yestaeUser.getUserId());
							recordWrapper.eq("invalid", UserConstant.VAS_RECORD_INVALID);
							recordWrapper.gt("end_time", new Date().getTime());
							recordWrapper.lt("begin_time", new Date().getTime());
							int count = recordService.selectCount(recordWrapper);
							//该用户已购买的增值服务尚未到期
							if(count > 0){
								Map<String, Object> warnMap = new HashMap<>();
								warnMap.put("name", name);
								warnMap.put("mobile", mobile);
								warnMap.put("warn", "导入失败，该用户已购买的增值服务尚未到期");
								warnList.add(warnMap);
								return;
							}
						}else{
							UserRegisterParameter userRegisterParameter = new UserRegisterParameter();
							//用户名为空则生成用户名
							if(StringUtils.isEmpty(name)){
								name = "Tea".concat(ToolUtil.getRandomString(8));
							}else if(name.length() > UserConstant.NAME_LENGTH){
								//用户名超过长度限制则截取用户名
								name = name.substring(0, UserConstant.NAME_LENGTH);
							}
							//查询用户名是否存在，存在则修改用户名
							Wrapper<YestaeUser> userNameWrapper = new EntityWrapper<>();
							userNameWrapper.eq("if_del", SysEnum.NO.getCode());
							userNameWrapper.eq("name", name);
							int userNameCount = yestaeUserService.selectCount(userNameWrapper);
							
							//用户名存在
							if(userNameCount > 0){
								//用户名存在标识
								Boolean nameFlag = true;
								//用户名长度小于长度限制，则在用户名后添加随机字符
								if(name.length() < UserConstant.NAME_LENGTH){
									String newName = name.concat(ToolUtil.getRandomString(UserConstant.NAME_LENGTH - name.length()));
									
									//再次查询用户名是否存在，存在则跳过该用户
									Wrapper<YestaeUser> userNameWrapper2 = new EntityWrapper<>();
									userNameWrapper2.eq("if_del", SysEnum.NO.getCode());
									userNameWrapper2.eq("name", newName);
									int reCount = yestaeUserService.selectCount(userNameWrapper2);
									if(reCount == 0){
										userRegisterParameter.setName(newName);
										nameFlag = false;
									}
								}
								//用户名已存在
								if(nameFlag){
									Map<String, Object> warnMap = new HashMap<>();
									warnMap.put("name", name);
									warnMap.put("mobile", mobile);
									warnMap.put("warn", "导入失败，用户名已存在");
									warnList.add(warnMap);
									return;
								}
								
							}else{
								userRegisterParameter.setName(name);
							}
							
							userRegisterParameter.setMobile(mobile);
							userRegisterParameter.setPassword(ToolUtil.getRandomString(8));
							
							//创建用户标识
							Boolean registerUserFlag = false;
							String registerUserCause = "";
							try {
								//用户不存在则创建新用户
								Map<String, Object> dubboMap = userCenterService.registerUser(userRegisterParameter);
								if(null != dubboMap && dubboMap.containsKey("userResult")){
									@SuppressWarnings("unchecked")
									UserResult<UserDubbo> result = (UserResult<UserDubbo>) dubboMap.get("userResult");
									if(null != result && result.isSucceed() && null != result.getResult()){
										userId = Convert.toLong(result.getResult().getUserId());
										logger.info("RecordController->importVasRecord->registerUser->userId:{}", userId);
										registerUserFlag = true;	
									}else if(null != result && !StringUtils.isEmpty(result.getRetMsg())){
										registerUserCause = "，原因：" + result.getRetMsg();
										logger.error("RecordController->importVasRecord->registerUser->error:{}", result.getRetMsg());
									}
								}
							} catch (Exception e) {
								logger.error("RecordController->importVasRecord->registerUser->error:{}", e.getMessage());
							}
							//创建用户失败
							if(!registerUserFlag){
								Map<String, Object> warnMap = new HashMap<>();
								warnMap.put("name", name);
								warnMap.put("mobile", mobile);
								warnMap.put("warn", "导入失败，创建用户失败".concat(registerUserCause));
								warnList.add(warnMap);
								return;
							}
						}
						
						AddedOrderParameter addedOrderParameter = new AddedOrderParameter();
						addedOrderParameter.setAddedServiceId(vasId);
						addedOrderParameter.setUserId(userId);
						addedOrderParameter.setPayAmount(new BigDecimal(0));
						
						Boolean saveAddedOrderFlag = false;
						String saveAddedOrderCause = "";
						try {
							//创建增值服务订单
							UserResult<String> saveAddedOrderResult = userCenterAddedService.saveAddedOrderByImportUser(addedOrderParameter);
							if(null != saveAddedOrderResult && saveAddedOrderResult.isSucceed() && !StringUtils.isEmpty(saveAddedOrderResult.getResult())){
								logger.info("RecordController->importVasRecord->saveAddedOrderByImportUser->orderNo:{}", saveAddedOrderResult.getResult());
								AddedOrderPaymentParameter addedOrderPaymentParameter = new AddedOrderPaymentParameter();
								addedOrderPaymentParameter.setUserId(userId);
								addedOrderPaymentParameter.setOrderNo(saveAddedOrderResult.getResult());
								addedOrderPaymentParameter.setPayState(VasConstants.VAS_ORDER_PAY_STATE_PAY_FINISH);
								
								
								
								Map<String, Object> AddedOrderPaymentMap = new HashMap<>();
								AddedOrderPaymentMap.put("addedOrderPaymentParameter", addedOrderPaymentParameter);
								AddedOrderPaymentMap.put("name", name);
								AddedOrderPaymentMap.put("mobile", mobile);
								addedOrderPaymentParameterList.add(AddedOrderPaymentMap);
								saveAddedOrderFlag = true;
							}else if(null != saveAddedOrderResult && !StringUtils.isEmpty(saveAddedOrderResult.getRetMsg())){
								saveAddedOrderCause = "，原因：".concat(saveAddedOrderResult.getRetMsg());
								logger.error("RecordController->importVasRecord->saveAddedOrderByImportUser->error:".concat(saveAddedOrderResult.getRetMsg()));
							}
						} catch (Exception e) {
							logger.error("RecordController->importVasRecord->saveAddedOrderByImportUser->error:{}", e.getMessage());
						}
						//创建增值服务订单失败
						if(!saveAddedOrderFlag){
							Map<String, Object> warnMap = new HashMap<>();
							warnMap.put("name", name);
							warnMap.put("mobile", mobile);
							warnMap.put("warn", "导入失败，创建增值服务订单失败".concat(saveAddedOrderCause));
							warnList.add(warnMap);
						}
					}
				
				});
			}
			executorService.shutdown();
			
			if(addedOrderPaymentParameterList.size() > 1){
				
				Thread.sleep(5000);
			}
			logger.info("addedOrderPaymentParameterList.size:" + addedOrderPaymentParameterList.size());
			for(Map<String, Object> AddedOrderPaymentMap: addedOrderPaymentParameterList){
				Boolean modifyAddedOrderPaymentFlag = false;
				String modifyAddedOrderPaymentCause = "";
				AddedOrderPaymentParameter addedOrderPaymentParameter = (AddedOrderPaymentParameter) AddedOrderPaymentMap.get("addedOrderPaymentParameter");
				try {
					//用户关联增值服务
//					logger.info("RecordController->importVasRecord->modifyAddedOrderPaymentByImportUser->mobile:"+MapUtils.getString(AddedOrderPaymentMap, "mobile")+"" + JSONObject.toJSONString(addedOrderPaymentParameter));
					UserResult<Boolean> modifyAddedOrderPaymentResult = userCenterAddedService.modifyAddedOrderPaymentByImportUser(addedOrderPaymentParameter);
					if(null != modifyAddedOrderPaymentResult && modifyAddedOrderPaymentResult.isSucceed() && modifyAddedOrderPaymentResult.getResult()){
						logger.info("RecordController->importVasRecord->modifyAddedOrderPayment->modifyAddedOrderPaymentResult:" + JSONObject.toJSONString(modifyAddedOrderPaymentResult));
						modifyAddedOrderPaymentFlag = true;
					}else if(null != modifyAddedOrderPaymentResult && !StringUtils.isEmpty(modifyAddedOrderPaymentResult.getRetMsg())){
						modifyAddedOrderPaymentCause = "，原因：".concat(modifyAddedOrderPaymentResult.getRetMsg());
						logger.error("RecordController->importVasRecord->modifyAddedOrderPaymentByImportUser->orderNo:{}, error:{}", addedOrderPaymentParameter.getOrderNo(), modifyAddedOrderPaymentResult.getRetMsg());
					}else{
						if(null != modifyAddedOrderPaymentResult){
							logger.error("RecordController->importVasRecord->modifyAddedOrderPaymentByImportUser->modifyAddedOrderPaymentResult:" + JSONObject.toJSONString(modifyAddedOrderPaymentResult));
							logger.error("RecordController->importVasRecord->modifyAddedOrderPaymentByImportUser->mobile:"+MapUtils.getString(AddedOrderPaymentMap, "mobile")+", addedOrderPaymentParameter:" + JSONObject.toJSONString(addedOrderPaymentParameter));
						}else{
							logger.error("RecordController->importVasRecord->modifyAddedOrderPaymentByImportUser->modifyAddedOrderPaymentResult:" + modifyAddedOrderPaymentResult);
						}
					}
				} catch (Exception e) {
					logger.error("RecordController->importVasRecord->modifyAddedOrderPaymentByImportUser->orderNo:{}, error:{}", addedOrderPaymentParameter.getOrderNo(), e.getMessage());
				}
				
				//用户关联增值服务失败
				if(!modifyAddedOrderPaymentFlag){
					Map<String, Object> warnMap = new HashMap<>();
					warnMap.put("name", MapUtils.getString(AddedOrderPaymentMap, "name"));
					warnMap.put("mobile", MapUtils.getString(AddedOrderPaymentMap, "mobile"));
					warnMap.put("warn", "导入失败，用户关联增值服务失败".concat(modifyAddedOrderPaymentCause));
					warnList.add(warnMap);
					continue;
				}
			}
			
			retMap.put("code", "200");
			retMap.put("msg", "操作成功");
		} catch (Exception e) {
			retMap.put("code", "0");
			retMap.put("msg", "操作失败");
			logger.error("RecordController->importVasRecord->error:{}", e.getMessage());
		}finally{
			if(null != in){
				try {
					in.close();
					in = null;
				} catch (IOException e) {
				}
			}
		}
		return retMap;
    }

	@RequestMapping(value = "/getInfo")
	@DataSource(name="dataSourceUc")
	@ResponseBody
	public Map<String, Object> getInfo(String recordId) {

		Map<String, Object> data = Maps.newHashMap();
		if (StringUtils.isBlank(recordId)) {
			return data;
		}

		//查询用户增值服
		Record record = recordService.selectById(recordId);
		if (record != null) {
			Long userId = record.getUserId();
			YestaeUser yestaeUser = yestaeUserService.selectByUserId(String.valueOf(userId));

			data.put("recordId", recordId);
			data.put("mobile", yestaeUser.getMobile());
		}

		return data;
	}

}
