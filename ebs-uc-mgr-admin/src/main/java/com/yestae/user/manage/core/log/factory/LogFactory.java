package com.yestae.user.manage.core.log.factory;

import java.util.Date;

import com.yestae.user.manage.common.constant.state.LogSucceed;
import com.yestae.user.manage.common.constant.state.LogType;
import com.yestae.user.manage.modular.system.persistence.model.LoginLog;
import com.yestae.user.manage.modular.system.persistence.model.OperationLog;

/**
 * 日志对象创建工厂
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午9:18:27
 */
public class LogFactory {

    /**
     * 创建操作日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:45
     */
    public static OperationLog createOperationLog(LogType logType, String userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId);
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreatetime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     *
     * @author fengshuonan
     * @Date 2017/3/30 18:46
     */
    public static LoginLog createLoginLog(LogType logType, String userId, String msg,String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId);
        loginLog.setCreatetime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
