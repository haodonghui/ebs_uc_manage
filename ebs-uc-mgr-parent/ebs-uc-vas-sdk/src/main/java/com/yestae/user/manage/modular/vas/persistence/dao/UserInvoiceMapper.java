package com.yestae.user.manage.modular.vas.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.manage.modular.vas.persistence.model.UserInvoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2020-08-03
 */
public interface UserInvoiceMapper extends BaseMapper<UserInvoice> {

    List<UserInvoice> selectUserInvoiceList(@Param("page") Page<UserInvoice> page, @Param("map") Map<String, Object> map);

    UserInvoice selectUserInvoiceById(String id);

}