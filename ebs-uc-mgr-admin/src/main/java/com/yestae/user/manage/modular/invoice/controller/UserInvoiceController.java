package com.yestae.user.manage.modular.invoice.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.center.dubbo.entity.UserInvoiceVerifyParameter;
import com.yestae.user.center.dubbo.entity.UserResult;
import com.yestae.user.center.dubbo.service.IUserCenterInvoiceService;
import com.yestae.user.center.dubbo.service.IUserCenterService;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.common.constant.UserInvoiceVerifyState;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroKit;
import com.yestae.user.manage.core.shiro.ShiroUser;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.FileUtil;
import com.yestae.user.manage.core.util.ImageUtil;
import com.yestae.user.manage.modular.vas.persistence.model.UserInvoice;
import com.yestae.user.manage.modular.vas.service.IUserInvoiceService;
import com.yestae.user.tmpl.dubbo.entity.PoaTmplDubbo;
import com.yestae.user.tmpl.dubbo.entity.PoaTmplResult;
import com.yestae.user.tmpl.dubbo.service.IPoaTmplDubboService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 增票审核控制器
 *
 * @author fengshuonan
 * @Date 2020-08-03 20:00:41
 */
@Controller
@RequestMapping("/userInvoice")
public class UserInvoiceController extends BaseController {

    private String PREFIX = "/invoice/userInvoice/";

    @Autowired
    private IUserInvoiceService userInvoiceService;
    @Resource
    private IUserCenterInvoiceService userCenterInvoiceService;
    @Resource
    private IPoaTmplDubboService poaTmplDubboService;
    @Resource
    private ImageUtil imageUtil;
    @Resource
    UcConstant ucConstant;

    /**
     * 跳转到增票审核首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userInvoice.html";
    }

    /**
     * 获取增票审核列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<UserInvoice> page = new PageFactory<UserInvoice>().defaultPage();
        Map<String, Object> paramMap = HttpKit.getRequestParametersMap();
        List<UserInvoice> userInvoices = userInvoiceService.selectUserInvoiceList(page, paramMap);
        for(UserInvoice ui: userInvoices){
            ui.setVerifyBy("NULL".equals(ui.getVerifyBy()) ? "" : ui.getVerifyBy());
            ui.setUserIdStr(String.valueOf(ui.getUserId()));
            ui.setCreateTimeStr(DateUtils.toDatetimeString(ui.getCreateTime()));
            if("3".equals(ui.getVerifyState()) || "2".equals(ui.getVerifyState()) || "4".equals(ui.getVerifyState())){
                ui.setUpdateTimeStr(DateUtils.toDatetimeString(ui.getUpdateTime()));
            }else{
                ui.setUpdateTimeStr("");
            }
            ui.setLarge(ucConstant.getImageServer() + ui.getLarge());
            ui.setVerifyStateName(CacheKit.get(Cache.CONSTANT, "verifyState:" + ui.getVerifyState()));
            if(ui.getRealNameFlag() != null && ui.getRealNameFlag() == 0){
                ui.setRealNameFlagName("是");
            }else{
                ui.setRealNameFlagName("否");
            }

        }
        page.setRecords(userInvoices);
        return super.packForBT(page);
    }



    /**
     * 跳转到增票详情或审核页面
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detailOrVerify/{userInvoiceId}")
    public Object detailOrVerify(@PathVariable("userInvoiceId") String userInvoiceId,Model model){
        UserInvoice userInvoice = userInvoiceService.selectUserInvoiceById(userInvoiceId);
        if(userInvoice != null && userInvoice.getVerifyState() != null){
            userInvoice.setThumb(ucConstant.getImageServer() + userInvoice.getThumb());
            userInvoice.setLarge(ucConstant.getImageServer() + userInvoice.getLarge());
            userInvoice.setCreateTimeStr(DateUtils.toDatetimeString(userInvoice.getCreateTime()));
            if("3".equals(userInvoice.getVerifyState()) || "2".equals(userInvoice.getVerifyState())){
                userInvoice.setUpdateTimeStr(DateUtils.toDatetimeString(userInvoice.getUpdateTime()));
            }else{
                userInvoice.setUpdateTimeStr("");
            }
            if(userInvoice.getRealNameFlag() != null && userInvoice.getRealNameFlag() == 0){
                userInvoice.setRealNameFlagName("是");
            }else{
                userInvoice.setRealNameFlagName("否");
            }
            userInvoice.setVerifyStateName(CacheKit.get(Cache.CONSTANT, "verifyState:" + userInvoice.getVerifyState()));
            userInvoice.setVerifyBy("NULL".equals(userInvoice.getVerifyBy()) ? "" : userInvoice.getVerifyBy());

            model.addAttribute("userInvoice",userInvoice);
            LogObjectHolder.me().set(userInvoice);

            if("1".equals(userInvoice.getVerifyState())){
                // 审核页面
                return PREFIX + "userInvoice_verify.html";

            }
            // 非待审核页面
            return PREFIX + "userInvoice_detail.html";
        }

        return null;
    }

    /**
     * 增票审核
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/verify")
    @ResponseBody
    public Object verify(UserInvoice userInvoice) {
        // 调用增票审核dubbo服务
        UserInvoiceVerifyParameter parameter = new UserInvoiceVerifyParameter();
        parameter.setUserId(String.valueOf(userInvoice.getUserId()));
        parameter.setInvoiceId(userInvoice.getId());
        parameter.setVerifyState(Integer.valueOf(userInvoice.getVerifyState()));
        parameter.setRemark(userInvoice.getRemark());
        ShiroUser user = ShiroKit.getUser();
        if(user != null){
            parameter.setVerifyBy(user.getName());
        }
        UserResult<Boolean> userResult = userCenterInvoiceService.modifyUserSpecialInvoiceVerifyState(parameter);

        if(userResult.getResult()){
            return SUCCESS_TIP;
        }

        throw new BussinessException(BizExceptionEnum.VERIFY_FAIL);
    }



    /**
     * 跳转到增票资质配置页面
     */
    @RequestMapping(value = "/userInvoice_config")
    public Object userInvoice_config(Model model){

        // 获取配置信息
        PoaTmplResult<PoaTmplDubbo> tmpl = poaTmplDubboService.getTmpl();
        if(tmpl != null){
            model.addAttribute("validQuantity", tmpl.getResult().getValidQuantity());
        }
        LogObjectHolder.me().set(model);
        return PREFIX + "userInvoice_config.html";
    }

    /**
     * 上传文件
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public Object upload(@RequestPart("file") MultipartFile file) {

        String originalFileName = file.getOriginalFilename();

        //校验图片类型
        String rex = ".*\\.(?i)(doc|docx)";
        if(!originalFileName.matches(rex)){
            throw new BussinessException(BizExceptionEnum.UPLOAD_TYPE_ERROR);
        }
        try {
            //文件类型
            String type = String.valueOf(originalFileName.substring(originalFileName.lastIndexOf(".")));
            //文件名称
            String fName = "授权委托书模板" + type;

            String path = ucConstant.getImageDir() + ucConstant.getPoaTmplFileDir();

            File tempFile = new File(path, fName);
            // 如果文件夹不存在则创建
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            file.transferTo(tempFile);

            // 调用dubbo服务保存模板信息
            PoaTmplDubbo poaTmplDubbo = new PoaTmplDubbo();
            poaTmplDubbo.setUrl(ucConstant.getPoaTmplFileDir() + "/" + fName);
            poaTmplDubbo.setDomain(ucConstant.getImageServer());
            poaTmplDubboService.savePoaTmpl(poaTmplDubbo);

            return SUCCESS_TIP;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
    }

    /**
     * 获取下载地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/download")
    public void download() {

        byte[] bytes = FileUtil.toByteArray(ucConstant.getImageDir() + ucConstant.getPoaTmplFileDir() + "/授权委托书模板.docx");
        OutputStream out = null;
        try {
            out = getHttpServletResponse().getOutputStream();
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 增票资质配置
     */
    @RequestMapping(value = "/config")
    @ResponseBody
    public Object config(UserInvoice userInvoice) {
        if(userInvoice.getValidQuantity() == null || userInvoice.getValidQuantity() == 0){
            throw new BussinessException(BizExceptionEnum.VALID_QUANTITY_CONFIG_ERROR);
        }
        // 调用dubbo服务保存有效增票资质数量
        PoaTmplDubbo poaTmplDubbo = new PoaTmplDubbo();
        poaTmplDubbo.setValidQuantity(userInvoice.getValidQuantity());
        poaTmplDubboService.savePoaTmpl(poaTmplDubbo);

        return SUCCESS_TIP;
    }

    /**
     * 撤销审核
     * @return
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public Object cancel(@RequestParam(required = false) String userInvoiceId){
        // 查询状态是否是：审核通过或审核不通过
        UserInvoice userInvoice = userInvoiceService.selectUserInvoiceById(userInvoiceId);

        if(userInvoice == null){
            throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
        }

        if(!(UserInvoiceVerifyState.VERIFY_STATE_NOT_PASS.equals(userInvoice.getVerifyState())
                || UserInvoiceVerifyState.VERIFY_STATE_PASS.equals(userInvoice.getVerifyState()))){
            throw new BussinessException(BizExceptionEnum.CANCEL_VERIFY_STATE_ERROR);
        }

        // 调用增票审核dubbo服务
        UserInvoiceVerifyParameter parameter = new UserInvoiceVerifyParameter();
        parameter.setUserId(String.valueOf(userInvoice.getUserId()));
        parameter.setInvoiceId(userInvoice.getId());
        // 设置状态为：待审核
        parameter.setVerifyState(Integer.valueOf(UserInvoiceVerifyState.VERIFY_STATE_WAIT));
        ShiroUser user = ShiroKit.getUser();
        if(user != null){
            parameter.setVerifyBy(user.getName());
        }
        UserResult<Boolean> userResult = userCenterInvoiceService.modifyUserSpecialInvoiceVerifyState(parameter);

        if(userResult.getResult()){
            return SUCCESS_TIP;
        }

        throw new BussinessException(BizExceptionEnum.CANCEL_VERIFY_ERROR);
    }

}
