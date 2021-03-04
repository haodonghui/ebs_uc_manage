package com.yestae.user.manage.modular.system.warpper;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.yestae.user.manage.common.constant.factory.ConstantFactory;
import com.yestae.user.manage.common.constant.state.SexName;
import com.yestae.user.manage.core.base.warpper.BaseControllerWarpper;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWarpper extends BaseControllerWarpper {

    public UserWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	map.put("sexName", SexName.valueOf((Integer) map.get("sex")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
        map.put("deptName", ConstantFactory.me().getDeptName((String) map.get("deptid")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
        map.put("birthday", MapUtils.getString(map, "birthday"));
    }

}
