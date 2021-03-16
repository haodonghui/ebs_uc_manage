package com.yestae.user.manage.modular.vas.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yestae.user.manage.modular.vas.persistence.model.UserInvoice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuqi
 * @since 2020-08-03
 */
public interface IUserInvoiceService extends IService<UserInvoice> {

    List<UserInvoice> selectUserInvoiceList(Page<UserInvoice> page, Map<String, Object> paramMap);

    UserInvoice selectUserInvoiceById(String id);
}
