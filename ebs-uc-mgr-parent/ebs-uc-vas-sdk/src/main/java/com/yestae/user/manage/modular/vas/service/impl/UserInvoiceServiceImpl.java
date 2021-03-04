package com.yestae.user.manage.modular.vas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.manage.modular.vas.persistence.dao.UserInvoiceMapper;
import com.yestae.user.manage.modular.vas.persistence.model.UserInvoice;
import com.yestae.user.manage.modular.vas.service.IUserInvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuqi
 * @since 2020-08-03
 */
@Service
public class UserInvoiceServiceImpl extends ServiceImpl<UserInvoiceMapper, UserInvoice> implements IUserInvoiceService {

    @Resource
    private UserInvoiceMapper userInvoiceMapper;

    @Override
    public List<UserInvoice> selectUserInvoiceList(Page<UserInvoice> page, Map<String, Object> paramMap) {
        return userInvoiceMapper.selectUserInvoiceList(page,paramMap);
    }

    @Override
    public UserInvoice selectUserInvoiceById(String id) {
        return userInvoiceMapper.selectUserInvoiceById(id);
    }
}
