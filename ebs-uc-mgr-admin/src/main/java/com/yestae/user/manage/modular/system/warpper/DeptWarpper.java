package com.yestae.user.manage.modular.system.warpper;

import java.util.Map;

import com.yestae.user.common.util.ToolUtil;
import com.yestae.user.manage.common.constant.factory.ConstantFactory;
import com.yestae.user.manage.core.base.warpper.BaseControllerWarpper;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DeptWarpper extends BaseControllerWarpper {

    public DeptWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {

    	String pid = (String) map.get("pid");

        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            map.put("pName", "--");
        } else {
            map.put("pName", ConstantFactory.me().getDeptName(pid));
        }
    }

}
