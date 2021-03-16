package com.yestae.user.manage.modular.privilege.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.privilege.persistence.model.CoinDetail;

import java.util.List;
import java.util.Map;

/**
 * 受益券明细服务
 *
 * @Package com.yestae.user.manage.modular.privilege.service.impl
 * @ClassName
 * @Author h_don
 * @Date 2020/3/30 15:56
 */

public interface IUserCoinDetailService extends IService<CoinDetail> {
    /**
     * 获取受益券明细
     *
     * @param page 分页
     * @param map 入参
     * @return java.util.List
     */
    List<Map<String, Object>> selectCoinDetail(Page<Map<String, Object>> page, Map<String, String> map);
}
